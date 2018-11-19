launch:
	emacs src/cljs/pequod_cljs/core.cljs & 
	lein figwheel

clean:
	lein clean
	rm -f figwheel_server.log
