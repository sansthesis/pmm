(ns pmm.views.layout
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [title data-main & content]
            (html5
              [:head
               [:title title]
               (include-css
                 "/static/css/global.css"
                 "/static/css/style.css")]
              [:body
               [:div#container
                content]
               [:script {:src "/static/scripts/require.js" :data-main data-main}]]))

(defn index
  [] (layout "Pmm"
             "/static/scripts/app"
             [:header
              [:h1 "pmm"]]
             [:div#main]
             [:footer]))
