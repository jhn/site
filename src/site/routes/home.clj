(ns site.routes.home
  (:require [site.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.coercions :refer [as-int]]))

(def all-photos (atom [{:url "/img/DSCF4961.jpg"}
                       {:url "/img/DSCF5027.jpg"}
                       {:url "/img/DSCF5034.jpg"}
                       {:url "/img/DSCF5051.jpg"}
                       {:url "/img/DSCF5039.jpg"}
                       {:url "/img/DSCF5048.jpg"}]))

(def page-size 5)

(def partitioned-photos (partition-all page-size @all-photos))

(def page-range
  (let [total-pages (Math/ceil (/ (count @all-photos) page-size))
        total-pages-range (range total-pages)]
    (set total-pages-range)))

(defn valid-index? [idx] (contains? page-range idx))

(defn pagination [idx]
  (let [next-index (inc idx)
        prev-index (dec idx)
        return-next (inc next-index)
        return-prev (inc prev-index)]
    {:next-page (when (valid-index? next-index) return-next)
     :prev-page (when (valid-index? prev-index) return-prev)}))

(defn home []
  (layout/render
   "home.html"
   (merge
     {:photos (first partitioned-photos)
      :total-pages (count partitioned-photos)}
     (pagination 0))))

(defn photos [page-num]
  (if (valid-index? page-num)
    (layout/render
      "home.html"
      (merge
        {:photos      (nth partitioned-photos page-num)
         :cur-page    (inc page-num)
         :total-pages (count partitioned-photos)}
        (pagination page-num)))
    (layout/render "error.html")))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/photos/:page" [page :<< as-int] (photos (dec page)))) ; dec for zero-based
