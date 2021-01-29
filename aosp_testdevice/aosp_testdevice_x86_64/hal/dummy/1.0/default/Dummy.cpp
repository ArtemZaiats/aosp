#define LOG_TAG "vendor.testdevice.dummy"
#include <android-base/logging.h>

#include <android-base/file.h>
// #include <hal_conversion.h>
#include <hidl/HidlTransportSupport.h>
#include <android-base/properties.h>
#include <thread>
#include "Dummy.h"

namespace vendor {
namespace testdevice {
namespace dummy {
namespace V1_0 {
namespace implementation {

// Methods from ::vendor::testdevice::dummy::V1_0::IDummy follow.
Return<::vendor::testdevice::dummy::V1_0::Result> Dummy::registerCallback(const sp<::vendor::testdevice::dummy::V1_0::IDummyCallback>& callback) {
        msg_ = android::base::GetProperty("vendor.testdevice.message", "test_string");
        std::lock_guard<std::mutex> _lock(callbacks_lock_);
        callback_ = callback;
        // unlock
    return ::vendor::testdevice::dummy::V1_0::Result::SUCCESS;
}

Return<::vendor::testdevice::dummy::V1_0::Result> Dummy::unregisterCallback(const sp<::vendor::testdevice::dummy::V1_0::IDummyCallback>& callback __unused) {
    callback_ = nullptr;
    return ::vendor::testdevice::dummy::V1_0::Result::SUCCESS;
}

Return<::vendor::testdevice::dummy::V1_0::Result> Dummy::update() {
    if (callback_) {
        msg_ = android::base::GetProperty("vendor.testdevice.message", "test_string");
        callback_->handleMsg(msg_);
        return ::vendor::testdevice::dummy::V1_0::Result::SUCCESS;
    }
    return ::vendor::testdevice::dummy::V1_0::Result::NOT_FOUND;
}

Return<void> Dummy::getMsg(getMsg_cb _hidl_cb __unused) {
    _hidl_cb(::vendor::testdevice::dummy::V1_0::Result::SUCCESS, msg_);
    return Void();
}

// Methods from ::android::hidl::base::V1_0::IBase follow.

//IDummy* HIDL_FETCH_IDummy(const char* /* name */) {
    //return new Dummy();
//}
//
}  // namespace implementation
}  // namespace V1_0
}  // namespace dummy
}  // namespace testdevice
}  // namespace vendor
