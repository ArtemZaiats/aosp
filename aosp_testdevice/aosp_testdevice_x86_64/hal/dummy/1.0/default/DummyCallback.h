// FIXME: your file license if you have one

#pragma once

#include <vendor/testdevice/dummy/1.0/IDummyCallback.h>
#include <hidl/MQDescriptor.h>
#include <hidl/Status.h>

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

struct DummyCallback : public IDummyCallback {
    // Methods from ::vendor::testdevice::dummy::V1_0::IDummyCallback follow.
    Return<void> handleMsg(const hidl_string& msg) override;

    // Methods from ::android::hidl::base::V1_0::IBase follow.

};

// FIXME: most likely delete, this is only for passthrough implementations
// extern "C" IDummyCallback* HIDL_FETCH_IDummyCallback(const char* name);

}  // namespace implementation
}  // namespace V1_0
}  // namespace dummy
}  // namespace testdevice
}  // namespace vendor
