#define LOG_TAG "vendor.testdevice.dummy@1.0-service"

#include <android-base/logging.h>
#include <binder/ProcessState.h>
#include <hidl/HidlTransportSupport.h>
#include <vendor/testdevice/dummy/1.0/IDummy.h>

#include "Dummy.h"

// Generated HIDL files
using namespace android::hardware;
using namespace vendor::testdevice::dummy::V1_0;

int main() {
    android::sp<IDummy> dummy = new implementation::Dummy();
    configureRpcThreadpool(1, true);

    const auto result = dummy->registerAsService();
    CHECK_EQ(result, android::OK);

    joinRpcThreadpool();
}
