(ns pmm.views.contacts
  (:use noir.core)
  (:require [noir.response :as response]
            [pmm.views.links :as links]
            [pmm.dao :as dao]))

(defpage "/contacts"
  [] (response/json {:links (lazy-cat [
                             (links/generate-contacts-link "self")
                             (links/generate-root-link "root")]
                             (map #(links/generate-contact-link (:id %) "item" (:email %)) (dao/list-contacts)))}))

(defpage "/contacts/:id" {:keys [id]}
  (let [entity (dao/get-contact-by-id id)]
    (if (nil? entity)
      (response/status 404 nil)
      (response/json (assoc entity
                       :links [(links/generate-contact-link (:id entity) "self" (:email entity))
                               (links/generate-contacts-link)])))))

(defpage [:post "/contacts"] {:keys [email] :as entity}
  (let [db-entity (dao/get-contact-by-email email)]
    (if (nil? db-entity)
      (response/json (dao/create-contact entity))
      (response/status 409 nil))))
