(ns sherbondy.style
  (:refer-clojure :exclude [+ - * /])
  (:require [sherbondy.constants :as c]
            [garden.arithmetic :refer [+ - * /]]
            [garden.color :as color :refer [as-hex hsl rgb rgba]]
            [garden.core :refer [css]]
            [garden.def :refer [defrule defkeyframes]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [percent px pt]]))

(def left-margin
  (+ c/half-width
     (* 3 2 c/half-width)))

(def debug-fg-color
  (rgba (conj c/fg-color 0.9)))

(defn url [link]
  (str "url('" link "')"))

(def left-pad
   (* 6 c/half-width))

(def max-width
  (* 12 2 c/half-width))

(css
 {:output-to "style.css"}
 [:*
  {:margin 0
   :padding 0
   :line-height 1}]
 [:body
  {:background {:image (url c/bg-img-name)
                :color (rgb c/bg-color)}
   :font-size (px 16)
   :font-family "'Source Sans Pro', sans-serif"
   :position :absolute
   :width (percent 100)
   :height (percent 100)
   }]
 [:#main
  {:background {:image (url c/fg-img-name)
                :repeat "repeat-y"
                :color ;;debug-fg-color
                (rgb c/fg-color)
                }
   :border-right "32px solid #000"
   :margin-left (px left-margin)
   :min-height (percent 100)}]
 [:#content
  {:padding {:left (px left-pad)
             :top (px (* 2 c/side-len))}
   :max-width (px max-width)}
  [:h1
   {:font-size (px (* 2 c/side-len))
    :margin-bottom (px c/side-len)}]
  [:p
   {:font-size (px (* 3/4 c/side-len))
    :line-height 1.5}]]
)
