#
# Copyright (C) 2017 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Bootanimation
PRODUCT_COPY_FILES += \
		device/generic/car/aosp_testdevice/aosp_testdevice_x86_64/bootanimation.zip:system/media/bootanimation.zip

# Overlays for device
DEVICE_PACKAGE_OVERLAYS += \
		device/generic/car/aosp_testdevice/aosp_testdevice_x86_64/overlay

$(call inherit-product, device/generic/car/common/car.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/aosp_x86_64.mk)

# Phony target that removes inherited packages
#PRODUCT_PACKAGES += remove_apps

# Dummy HAL
PRODUCT_PACKAGES += vendor.testdevice.dummy@1.0-service

# Dummy app
PRODUCT_PACKAGES += DummyApp

# Dummy service
PRODUCT_PACKAGES += DummyService

PRODUCT_NAME := aosp_testdevice_x86_64
PRODUCT_MANUFACTURER := TestDevice
PRODUCT_DEVICE := generic_x86_64
PRODUCT_BRAND := Android
PRODUCT_MODEL := Car on x86_64 emulator

# Vendor Interface Manifest
DEVICE_MANIFEST_FILE := device/generic/car/aosp_testdevice/aosp_testdevice_x86_64/manifest.xml

BOARD_VENDOR_SEPOLICY_DIRS += device/generic/car/aosp_testdevice/aosp_testdevice_x86_64/sepolicy/vendor
BOARD_PLAT_PUBLIC_SEPOLICY_DIR += device/generic/car/aosp_testdevice/aosp_testdevice_x86_64/sepolicy/public
BOARD_PLAT_PRIVATE_SEPOLICY_DIR += device/generic/car/aosp_testdevice/aosp_testdevice_x86_64/sepolicy/private
