(ns pmm.views.root
  (:use noir.core)
  (:require [noir.response :as response]
            [pmm.views.links :as links]
            [pmm.views.layout :as layout]
            [rest.common :as common]))

(defpage "/"
  [] (response/json {:links [
                             (links/generate-root-link "self")
                             (links/generate-customers-link)]}))

(defpage "/index.html"
  [] (response/redirect (common/full-url-for (str "/index.html/#!/root?uri=" (common/full-url-for "/")))))

(defpage "/index.html/"
  [] (layout/index))
