(ns sherbondy.constants)

(def side-len 32)
(def quarter-height (* side-len (Math/cos (/ Math/PI 3))))
(def half-width  (* side-len (Math/sin (/ Math/PI 3))))

(def bg-color [96 0 96])
(def fg-color [128 128 0])

(def bg-img-name "hex-bg-tile.png")
(def fg-img-name "hex-fg-tile.png")
(def simple-img-name "hex-simple.png")
(def nav-img-name "hex-nav.png")
(def nav-hover-img-name "hex-nav-hover.png")
