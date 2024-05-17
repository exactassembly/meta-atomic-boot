SUMMARY = "Bare "

IMAGE_INSTALL = " "
PACKAGE_INSTALL = " "

IMAGE_FEATURES = " "

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

APPLICATION_NAME ?= "empty-application"
APPLICATION_RECIPE ?= "${APPLICATION_NAME}-image"
APPLICATION_IMAGE_FILE := "${APPLICATION_RECIPE}-${MACHINE}.squashfs"
INITRAMFS_IMAGE ?= "initramfs-overlayfs-image"

DEPENDS+=" \
    virtual/kernel \
    virtual/bootloader \
    ${INITRAMFS_IMAGE} \
    ${APPLICATION_RECIPE} \
    "

IMAGE_ROOTFS_SIZE = "262144"
export IMAGE_BASENAME = "system-wic-image"
IMAGE_FSTYPES = "wic"


IMAGE_BOOT_FILES += " \
    ${APPLICATION_IMAGE_FILE};application-rootfs.squashfs \
    ${@bb.utils.contains('KERNEL_IMAGETYPE', 'fitImage', 'fitImage-%s-%s;fitImage-initramfs' % (d.getVar('INITRAMFS_IMAGE_NAME'), d.getVar('MACHINE')), '', d)} \
    "
