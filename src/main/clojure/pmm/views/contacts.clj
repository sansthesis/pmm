(ns pmm.views.contacts
  (:use noir.core)
  (:require [noir.response :as response]
            [pmm.views.links :as links]
            [pmm.dao :as dao]))

(defpage get-contacts "/contacts"
  [] (response/json {:links (conj (map #(links/generate-contact-link {:id (:id %) :rel "item" :title (:email %)}) (dao/list-contacts))
                                  (links/generate-contacts-link {:rel "self"})
                                  (links/generate-root-link {:rel "root"}))}))

(defpage get-contact-by-id "/contacts/:id" {:keys [id]}
  (let [entity (dao/get-contact-by-id id)]
    (if (nil? entity)
      (response/status 404 nil)
      (response/json (assoc entity
                       :links [(links/generate-contact-link {:id (:id entity) :rel "self" :title (:email entity)})
                               (links/generate-contacts-link)])))))

(defpage create-contact [:post "/contacts"] {:keys [email] :as entity}
  (let [db-entity (dao/get-contact-by-email email)]
    (if (nil? db-entity)
      (response/json (dao/create-contact entity))
      (response/status 409 nil))))
