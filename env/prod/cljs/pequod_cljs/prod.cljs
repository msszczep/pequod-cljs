(ns pequod-cljs.prod
  (:require [pequod-cljs.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
