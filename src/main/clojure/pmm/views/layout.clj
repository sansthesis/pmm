(ns pmm.views.layout
  (:use [noir.core :only [defpartial]]
        [hiccup core page-helpers]))

(defn- include-clojurescript [path]
  (list (javascript-tag "var CLOSURE_NO_DEPS = true;")
        (include-js path)))

(defn heading
  ([] (html [:p "this is the heading"])))

(defn profile
  ([] (html [:p "this is the profile"])))

(defn footer
  ([] (html [:p"this is the footer"])))

(defn layout
  ([title & content]
    (html5
      [:head
       [:title title]
       (include-css
         "/static/css/global.css"
         "/static/css/style.css")
       (include-clojurescript "/static/script/js.js")]
      [:body
       [:div#container
        [:header#heading (heading)]
        [:header#profile (profile)]
        [:section#content content]
        [:footer (footer)]
        ;(javascript-tag "pmm.say_hello()")
        ]])))

(defn index
  ([] (layout "Pmm"
              [:div#main
               [:p "this is the body"]])))
  