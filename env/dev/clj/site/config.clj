(ns site.config
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [site.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[site started successfully using the development profile]=-"))
   :middleware wrap-dev})
