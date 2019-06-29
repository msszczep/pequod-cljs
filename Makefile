launch:
	emacs src/cljs/pequod_cljs/core.cljs &
	emacs test/cljs/pequod_cljs/core_test.cljs &
	lein figwheel

core:
	emacs src/cljs/pequod_cljs/core.cljs &

doo:
	emacs src/cljs/pequod_cljs/core.cljs &
	emacs test/cljs/pequod_cljs/core_test.cljs &
	lein doo

data:
	emacs src/clj/pequod_cljs/data.clj &

pgd:
	emacs src/cljs/pequod_cljs/ex001ppgdata.cljs &

clean:
	lein clean
	rm -f figwheel_server.log
