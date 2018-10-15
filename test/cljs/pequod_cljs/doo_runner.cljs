(ns pequod-cljs.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [pequod-cljs.core-test]))

(doo-tests 'pequod-cljs.core-test)
