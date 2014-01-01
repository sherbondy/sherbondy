(ns sherbondy.style
  (:refer-clojure :exclude [+ - * /])
  (:require [sherbondy.constants :as c]
            [garden.arithmetic :refer [+ - * /]]
            [garden.color :as color :refer [as-hex hsl rgb]]
            [garden.core :refer [css]]
            [garden.def :refer [defrule defkeyframes]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :as u :refer [percent px pt]]))

(def left-margin
  (+ c/half-width
     (* 3 2 c/half-width)))

(defn url [link]
  (str "url('" link "')"))

(css
 {:output-to "style.css"}
 [:*
  {:margin 0
   :padding 0
   :line-height 1}]
 [:body
  {:background (str (url c/bg-img-name)
                    " "
                    (as-hex (rgb c/bg-color)))
   :font-size (px 16)
   :font-family "Futura, sans"
   :position :absolute
   :width (percent 100)
   :height (percent 100)
   }]
 [:#main
  {:background (str (url c/fg-img-name)
                    (as-hex (rgb c/fg-color))
                    " repeat-y")
   :margin-left (px left-margin)
   :min-height (percent 100)}]
 [:#content
  {:padding (px 32)}]
)
