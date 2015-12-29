(ns site.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[site started successfully]=-"))
   :middleware identity})
