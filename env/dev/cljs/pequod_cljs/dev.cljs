(ns ^:figwheel-no-load pequod-cljs.dev
  (:require
    [pequod-cljs.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
