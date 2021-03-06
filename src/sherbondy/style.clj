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
  (dec (* 5 c/half-width)))

(def debug-fg-color
  (rgba (conj c/fg-color 0.9)))

(defn url [link]
  (str "url('" link "')"))

(def left-pad
   (* 7 c/half-width))

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
                :color (rgb c/bg-color)
                :position-y (px (* 1.5 c/side-len))}
   :font-size (px 16)
   :font-family "'Source Sans Pro', sans-serif"
   :position :absolute
   :width (percent 100)
   :height (percent 100)}]

 [:#main
  {:background {:image (url c/fg-img-name)
                :repeat "repeat-y"
                :position-y (px (* 1 c/side-len))
                :color ;;debug-fg-color
                (rgb c/fg-color)}
   :margin {:top  (px (* 1 c/side-len))
            :left (px left-margin)}
   :min-height (percent 100)}]

 [:#logo
  {:position :absolute
   :left (px (* 4 2 c/half-width))
   :top  (px (* 7/2 c/side-len))}]

 [:#content
  {:padding {:left  (px left-pad)
             :right (px c/half-width)}
   :max-width (px max-width)}
  [:h1
   {:font-size (px (* 2 c/side-len))
    :margin {:bottom (px (* 1 c/side-len))}}]
  [:p
   {:font-size (px (* 3/4 c/side-len))
    :line-height 1.5}
   [:a
    {:color (rgb 0 0 0)
     :border-bottom (str "2px solid "
                         (as-hex (rgb c/bg-color)))
     :text-decoration :none}
    [:&:hover :&:focus
     {:background (rgb 255 255 255)
      :border-bottom "2px solid #000"}]]]]

 [:#nav
  {:display :block
   :overflow :hidden
   :height (px (* 2 c/side-len))
   :margin {:top    (px (* -0.5 c/side-len))
            :left   (px (* 0 c/half-width))
            :bottom (px c/side-len)}
   :padding {:left (px (* 3 c/half-width))}
   :font-size (px (* 3/4 c/side-len))
   :font-weight :bold
   :background {:color (rgb 0 0 0)
                :image (url c/nav-img-name)
                :repeat "x"
                :position-x (px (* -1 c/half-width))
                :position-y (px (* 0 c/side-len))}}
  [:li {:list-style :none
        :float :left}
   [:a {:display :block
        :float :left
        :width (px (* 6 c/half-width))
        :text-decoration :none
        :text-align :center
        :padding {:top    (px (* 5/8 c/side-len))
                  :bottom (px (* 5/8 c/side-len))}
        :color (rgb 255 255 255)}
    [:&:hover
     {:background
      {:color (rgb 255 255 255)
       :image (url c/nav-hover-img-name)
       :repeat "x"
       :position-y (px (* 0 c/side-len))}
     :color (rgb 0 0 0)}]]]]
)
