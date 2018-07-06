# Copyright (C) 2016 Freescale Semiconductor
# Copyright 2017-2018 NXP

DESCRIPTION = "i.MX System Controller Firmware"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=5ab1a30d0cd181e3408077727ea5a2db"
SECTION = "BSP"

inherit fsl-eula-unpack pkgconfig deploy

SRC_URI = " \
     ${FSL_MIRROR}/${PN}-${PV}.bin;fsl-eula=true \
     file://apalis-imx8-scfw-tcm.bin \
     file://colibri-imx8qxp-scfw-tcm.bin \
"

SRC_URI[md5sum] = "3246a44a242b68fae601561a80d5925c"
SRC_URI[sha256sum] = "00024e0dd332b402df03b62eac9a515fabc903568d0ad7f30fabc7c98b494f15"

S = "${WORKDIR}/${PN}-${PV}"

BOARD_TYPE ?= "mek"
SC_FIRMWARE_NAME ?= "mx8qm-mek-scfw-tcm.bin"
SC_FIRMWARE_NAME_mx8qm = "mx8qm-${BOARD_TYPE}-scfw-tcm.bin"
SC_FIRMWARE_NAME_mx8qxp = "mx8qx-${BOARD_TYPE}-scfw-tcm.bin"
SC_FIRMWARE_NAME_apalis-imx8 = "apalis-imx8-scfw-tcm.bin"
SC_FIRMWARE_NAME_colibri-imx8qxp = "colibri-imx8qxp-scfw-tcm.bin"
symlink_name = "scfw_tcm.bin"

SYSROOT_DIRS += "/boot"

do_patch () {
    cp ${WORKDIR}/*-scfw-tcm.bin ${S}/
}

do_install () {
    install -d ${D}/boot
    install -m 0644 ${S}/${SC_FIRMWARE_NAME} ${D}/boot/
}

BOOT_TOOLS = "imx-boot-tools"

do_deploy () {
    install -d ${DEPLOYDIR}/${BOOT_TOOLS}
    install -m 0644 ${S}/${SC_FIRMWARE_NAME} ${DEPLOYDIR}/${BOOT_TOOLS}/
    cd ${DEPLOYDIR}/${BOOT_TOOLS}/
    rm -f ${symlink_name}
    ln -sf ${SC_FIRMWARE_NAME} ${symlink_name}
    cd -
}

addtask deploy after do_install

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES_${PN} = "/boot"

COMPATIBLE_MACHINE = "(mx8qm|mx8qxp)"
