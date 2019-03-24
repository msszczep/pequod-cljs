launch:
	emacs src/cljs/pequod_cljs/core.cljs & 
	emacs test/cljs/pequod_cljs/core_test.cljs & 
	lein figwheel

data:
	emacs src/clj/pequod_cljs/data.clj &

clean:
	lein clean
	rm -f figwheel_server.log
