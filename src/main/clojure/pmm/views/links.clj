(ns pmm.views.links
  (:require [rest.common :as common]
            [noir.core :as noir]))

(defn generate-link
  ([vals] (common/remove-null-values vals)))

(defn generate-root-link
  ([] (generate-root-link {}))
  ([{:keys [rel type] :or {rel "root" type "application/json"}}] 
    (generate-link {:rel rel
                    :type type
                    :href (common/full-url-for (noir/url-for pmm.views.root/root))})))

(defn generate-contact-link
  ([{:keys [id rel title type] :or {rel "contact" type "application/json"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.contacts/get-contact-by-id {:id id}))})))

(defn generate-contacts-link
  ([] (generate-contacts-link {}))
  ([{:keys [rel type title] :or {rel "contacts" type "application/json" title "Contacts"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.contacts/get-contacts))})))

(defn generate-organizations-link
  ([] (generate-organizations-link {}))
  ([{:keys [rel type title] :or {rel "organizations" type "application/json" title "Organizations"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.organizations/get-organizations))})))

(defn generate-organization-link
  ([{:keys [id rel title type] :or {rel "organization" type "application/json"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.organizations/get-organization-by-id {:id id}))})))

(defn generate-divisions-by-organization-link
  ([] (generate-divisions-by-organization-link {}))
  ([{:keys [id rel type title] :or {rel "divisions" type "application/json" title "Divisions"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.organizations/get-divisions-by-organization {:organization id}))})))

(defn generate-division-by-organization-link
  ([] (generate-division-by-organization-link {}))
  ([{:keys [organization id rel type title] :or {rel "divisions" type "application/json" title "Divisions"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.organizations/get-division-by-organization {:organization organization :id id}))})))
