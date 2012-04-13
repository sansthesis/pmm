(ns pmm.views.organizations
  (:use noir.core)
  (:require [noir.response :as response]
            [pmm.views.links :as links]
            [pmm.dao :as dao]))

(defpage get-organizations "/organizations"
  [] (response/json {:links (conj (map #(links/generate-organization-link {:id (:id %) :rel "item" :title (:name %)}) (dao/list-organizations))
                                  (links/generate-organizations-link {:rel "self"})
                                  (links/generate-root-link {:rel "root"}))}))

(defpage get-organization-by-id "/organizations/:id" {:keys [id]}
  (let [entity (dao/get-organization-by-id id)]
    (if (nil? entity)
      (response/status 404 nil)
      (response/json (assoc entity
                       :links [(links/generate-organization-link {:id (:id entity) :rel "self" :title (:email entity)})
                               (links/generate-organizations-link {:rel "collection"})
                               (links/generate-divisions-by-organization-link {:id (:id entity)})])))))

(defpage create-organization [:post "/organizations"] {:keys [id] :as entity}
  (let [db-entity (dao/get-organization-by-id id)]
    (if (nil? db-entity)
      (response/json (dao/create-organization entity))
      (response/status 409 nil))))

(defpage create-division [:post "/organizations/:organization/divisions"] {:keys [organization] :as entity}
  (let [db-entity (dao/get-organization-by-id organization)]
    (if (nil? db-entity)
      (response/status 409 nil)
      (response/json (dao/create-division (assoc entity :organization organization))))))

(defpage get-divisions-by-organization "/organizations/:organization/divisions" {:keys [organization]}
  (let [entity (dao/get-organization-by-id organization)]
    (if (nil? entity)
      (response/status 404 nil)
      (response/json (assoc entity
                       :links (conj (map #(links/generate-division-by-organization-link {:id (:id %) :organization organization :rel "item" :title (:name %)}) (dao/list-divisions-by-organization organization))
                                    (links/generate-divisions-by-organization-link {:id organization :rel "self"})
                                    (links/generate-organization-link {:id organization :title (:email entity)})))))))

(defpage get-division-by-organization "/organizations/:organization/divisions/:id" {:keys [organization id]}
  (let [org (dao/get-organization-by-id organization)
        entity (dao/get-division-by-id id)]
    (if (or (nil? entity) (nil? org) (not= (:organization entity) (:id org)))
      (response/status 404 nil)
      (response/json (assoc entity
                       :links (list (links/generate-division-by-organization-link {:id id :organization organization :rel "self" :title (:name entity)})
                                    (links/generate-organization-link {:id organization :title (:name org) :rel "organization"})))))))
