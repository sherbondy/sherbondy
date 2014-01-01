(ns sherbondy.core
  (:require [sherbondy.constants :as c])
  (:use quil.core)
  (:import javax.imageio.ImageIO))

(def bg-origin [(* 2 c/half-width)
                (+ c/side-len c/quarter-height)])

(def fg-origin [c/half-width
                (+ c/side-len c/quarter-height)])

(defn hex-point [[center-x center-y] side-len i]
  (let [angle (* (* 2 Math/PI 1/6) (+ i 0.5))]
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
  (hexagon [(- (bg-origin 0) c/half-width)
            (- (bg-origin 1) c/side-len c/quarter-height)]
           c/side-len)
  (hexagon [(+ (bg-origin 0) c/half-width)
            (- (bg-origin 1) c/side-len c/quarter-height)]
           c/side-len)

  (hexagon bg-origin c/side-len)

  (hexagon [(- (bg-origin 0) c/half-width)
            (+ (bg-origin 1) c/side-len c/quarter-height)]
           c/side-len)
  (hexagon [(+ (bg-origin 0) c/half-width)
            (+ (bg-origin 1) c/side-len c/quarter-height)]
           c/side-len)

  (save-frame c/bg-img-name))

(defn setup-fg []
  (smooth)
  (apply background bg-color)
  (apply stroke fg-color)
  (stroke-weight 2)
  (apply fill fg-color)

  (hexagon [(+ c/half-width (fg-origin 0))
            (- (fg-origin 1) c/side-len c/quarter-height)]
           c/side-len)
  (hexagon fg-origin c/side-len)
  (hexagon [(+ c/half-width (fg-origin 0))
            (+ (fg-origin 1) c/side-len c/quarter-height)]
           c/side-len)

  (save-frame c/fg-img-name))

(defsketch bg-sketch
  :title "hex background"
  :setup setup-bg
  :size [(* 4 c/half-width)
         (* 3 c/side-len)])

(defsketch fg-sketch
  :title "hex foreground"
  :setup setup-fg
  :size [(* 2 c/half-width)
         (* 3 c/side-len)])
