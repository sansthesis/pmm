(ns pmm.views.customers
  (:use noir.core)
  (:require [noir.response :as response]
            [pmm.views.links :as links]
            [pmm.dao :as dao]))

(defpage "/customers"
  [] (response/json {:links (lazy-cat [
                             (links/generate-customers-link "self")
                             (links/generate-root-link "root")]
                             (map #(links/generate-customer-link (:id %) "item" (:email %)) (dao/list-customers)))}))

(defpage "/customers/:id" {:keys [id]}
  (let [entity (dao/get-customer-by-id id)]
    (if (nil? entity)
      (response/status 404 nil)
      (response/json (assoc entity
                       :links [(links/generate-customer-link (:id entity) "self" (:email entity))
                               (links/generate-customers-link)])))))

(defpage [:post "/customers"] {:keys [email] :as entity}
  (let [db-entity (dao/get-customer-by-email email)]
    (if (nil? db-entity)
      (response/json (dao/create-customer entity))
      (response/status 409 nil))))
