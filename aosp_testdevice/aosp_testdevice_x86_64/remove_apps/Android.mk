LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := remove_apps
LOCAL_MODULE_TAG := optional
PACKAGES.$(LOCAL_MODULE).OVERRIDES := \
	Bluetooth \
    BluetoothMidiService \
    BookmarkProvider \
    Browser2 \
    Calendar \
    CalendarProvider \
    Camera2 \
    CaptivePortalLogin \
    CarDialerApp \
    CarRadioApp \
    CarrierConfig \
    com.android.phone.common \
    Contacts \
    ContactsProvider \
    DeskClock \
    DocumentsUI \
    DownloadProvider \
    DownloadProviderUi \
    EasterEgg \
    EmbeddedKitchenSinkApp \
    EmergencyInfo \
    ExternalStorageProvider \
    Gallery2 \
    GarageModeTestApp \
    HTMLViewer \
    LiveWallpapersPicker \
    LocalMediaPlayer \
    MmsService \
    MtpDocumentsProvider \
    Music \
    MusicFX \
    NfcNci \
    PhotoTable \
    PrintRecommendationService \
    PrintSpooler \
    Provision \
    QuickSearchBox \
    Settings \
    SettingsIntelligence \
    SharedStorageBackup \
    SimAppDialog \
    StorageManager \
    Telecom \
    TelephonyProvider \
    TelephonyProvider \
    TeleService \
    Traceur \
    UserDictionaryProvider \
    VmsPublisherClientSample \
    VmsSubscriberClientSample \
    VpnDialogs \
    WallpaperBackup \
    WallpaperCropper \
    WallpaperPicker \
		webview

include $(BUILD_PHONY_PACKAGE)
