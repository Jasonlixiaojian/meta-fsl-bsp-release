# Copyright 2017-2018 NXP

require imx-vpu-hantro.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=ab61cab9599935bfe9f700405ef00f28"

SRC_URI[md5sum] = "4cbe355ad1e3d628f97860444ec942ea"
SRC_URI[sha256sum] = "e5f6147bfb7db90503e489412387c3d1daebd03141dc40a0c03ccfb9dd3de7a7"

# Compatible only for i.MX with Hantro VPU
COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_imxvpuhantro = "${MACHINE}"