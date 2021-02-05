// FIXME: your file license if you have one

#pragma once

#include <vendor/testdevice/dummy/1.0/IDummy.h>
#include <hidl/MQDescriptor.h>
#include <hidl/Status.h>
#include <thread>
#include <vector>

namespace vendor {
namespace testdevice {
namespace dummy {
namespace V1_0 {
namespace implementation {

using ::android::hardware::hidl_array;
using ::android::hardware::hidl_memory;
using ::android::hardware::hidl_string;
using ::android::hardware::hidl_vec;
using ::android::hardware::Return;
using ::android::hardware::Void;
using ::android::sp;

struct Dummy : public IDummy {
    // Methods from ::vendor::bmw::dummy::V1_0::IDummy follow.
    Return<::vendor::testdevice::dummy::V1_0::Result> registerCallback(const sp<::vendor::testdevice::dummy::V1_0::IDummyCallback>& callback) override;
    Return<::vendor::testdevice::dummy::V1_0::Result> unregisterCallback(const sp<::vendor::testdevice::dummy::V1_0::IDummyCallback>& callback) override;
    Return<::vendor::testdevice::dummy::V1_0::Result> update() override;
    Return<void> getMsg(getMsg_cb _hidl_cb) override;

    // Methods from ::android::hidl::base::V1_0::IBase follow.

private:
    void thread_task();
	  std::atomic<bool>  mThreadExit { false };
	  std::thread mThread;
    std::mutex callbacks_lock_;
    sp<IDummyCallback> callback_;
    hidl_string msg_;
};

// FIXME: most likely delete, this is only for passthrough implementations
// extern "C" IDummy* HIDL_FETCH_IDummy(const char* name);

}  // namespace implementation
}  // namespace V1_0
}  // namespace dummy
}  // namespace testdevice
}  // namespace vendor
