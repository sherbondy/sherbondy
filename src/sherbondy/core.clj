(ns sherbondy.core
  (:use quil.core)
  (:import javax.imageio.ImageIO))


(def side-len 32)
(def half-height (* side-len (Math/sin (/ PI 3))))
(def half-width  (* side-len (Math/cos (/ PI 3))))

(def bg-origin [(+ side-len half-width)
                half-height])
(def fg-origin [(+ side-len half-width)
                half-height])

(def bg-color [128 0 128])
(def fg-color [128 128 0])

(defn hex-point [[center-x center-y] side-len i]
  (let [angle (* (* 2 Math/PI 1/6) i)]
    [(+ center-x (* side-len (Math/cos angle)))
     (+ center-y (* side-len (Math/sin angle)))]))

(defn hexagon [center side-len]
  (begin-shape)
  (let [points (vec (map (partial hex-point center side-len)
                         (range 6)))]
    (doseq [point points]
      (apply vertex point)))
  (end-shape :close))


(defn setup-bg []
  (smooth)
  (apply background bg-color)
  (apply fill bg-color)
  (apply stroke fg-color)
  (stroke-weight 2)
  (hexagon [(- (bg-origin 0) side-len half-width)
            (+ (bg-origin 1) half-height)]
           side-len)
  (hexagon bg-origin side-len)
  (hexagon [(bg-origin 0)
            (+ (bg-origin 1) (* 2 half-height))]
           side-len)
  (hexagon [(+ (bg-origin 0) side-len half-width)
            (+ (bg-origin 1) half-height)]
           side-len)
  (save-frame "hex-bg-tile.png"))

(defn setup-fg []
  (smooth)
  (apply background bg-color)
  (apply stroke fg-color)
  (stroke-weight 0)
  (apply fill fg-color)
  (hexagon [(- (fg-origin 0) side-len half-width)
            (+ (fg-origin 1) half-height)]
           side-len)
  (hexagon fg-origin side-len)
  (hexagon [(fg-origin 0)
            (+ (fg-origin 1) (* 2 half-height))]
           side-len)
  (hexagon [(+ (fg-origin 0) side-len half-width)
            (+ (fg-origin 1) half-height)]
           side-len)
  (save-frame "hex-fg-tile.png"))

(defsketch bg-sketch
  :title "hex background"
  :setup setup-bg
  :size [(* 2 (+ side-len half-width))
         (* 4 half-height)])

(defsketch fg-sketch
  :title "hex foreground"
  :setup setup-fg
  :size [(* 2 (+ side-len half-width))
         (* 4 half-height)])
