// FIXME: your file license if you have one

#include "DummyCallback.h"

namespace vendor {
namespace testdevice {
namespace dummy {
namespace V1_0 {
namespace implementation {

// Methods from ::vendor::testdevice::dummy::V1_0::IDummyCallback follow.
Return<void> DummyCallback::handleMsg(const hidl_string& msg __unused) {
    // TODO implement
    return Void();
}


// Methods from ::android::hidl::base::V1_0::IBase follow.

//IDummyCallback* HIDL_FETCH_IDummyCallback(const char* /* name */) {
    //return new DummyCallback();
//}
//
}  // namespace implementation
}  // namespace V1_0
}  // namespace dummy
}  // namespace testdevice
}  // namespace vendor
