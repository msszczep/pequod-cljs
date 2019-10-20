(ns pequod-cljs.metasolutions)

; to use:
; output n
; effort-space n
; x-space n

(defn output-numerator [n]
  (str "(+"
       " (- (* k (Math/log a)))"
       " (* c (Math/log k))"
       " (* c (Math/log s))"
       " (- (* c (Math/log λ)))"
       " (- (* c (Math/log c)))"
       (apply str (map #(format " (- (* b%s k (Math/log b%s))) (* b%s k (Math/log p%s)) (- (* b%s k (Math/log λ)))" % % % % % %) 
                        (range 1 (inc n))))
       ")"))


(defn output-denominator [n]
  (str "(+ c (- k)"
       (apply str (map #(format " (* k b%s)" %) 
                       (range 1 (inc n))))
       ")"))

(defn output [n]
  (str "(Math/pow Math/E (/ "
       (output-numerator n)
       " "
       (output-denominator n)
       "))" ))

(defn x-space-numerator [n x]
  (let [all-but-x (remove #(= x %) 
                          (range 1 (inc n)))] 
    (str "(+"
         " (- (* k (Math/log a)))"
         (apply str (map #(format " (* b%s k (Math/log b%s))" % x) 
                        all-but-x))
         (format " (* c (Math/log b%s))" x)
         (format " (- (* k (Math/log b%s)))" x) 
         (apply str (map #(format " (- (* b%s k (Math/log b%s)))" % %) 
                         all-but-x))
         " (- (* c (Math/log c)))"
         " (* c (Math/log k))"
         (apply str (map #(format " (- (* b%s k (Math/log p%s)))" % x) 
                         all-but-x))
         (format " (* k (Math/log p%s))" x)
         (format " (- (* c (Math/log p%s)))" x)
         (apply str (map #(format " (* b%s k (Math/log p%s))" % %) 
                         all-but-x))
         " (* c (Math/log s))"
         " (- (* k (Math/log λ)))"
         ")")))

(defn x-space [n x]
  (str "(Math/pow Math/E (/ "
       (x-space-numerator n x)
       " "
       (output-denominator n)
       "))" ))


(defn effort-even-numerator [n]
  (let [n-range (range 1 (inc n))] 
    (str "(+"
         " (- (* (Math/log a)))"
         (apply str (map #(format " (- (* b%s (Math/log b%s)))" % %) 
                         n-range))
         " (- (* (Math/log c)))" 
         (apply str (map #(format " (* b%s (Math/log c))" %) 
                         n-range))
         " (* (Math/log k))"
         (apply str (map #(format " (* b%s (Math/log k))" %) 
                         n-range))
         (apply str (map #(format " (* b%s (Math/log p%s))" % %) 
                         n-range))
         " (* (Math/log s))"
         (apply str (map #(format " (* b%s (Math/log s))" %) 
                         n-range))
         " (- (* (Math/log λ)))"
         ")")))


(defn effort-odd-preamble [n]
  (let [n-range (range 1 (inc n))] 
    (str " (- (* (Math/log a)))"
         (apply str (map #(format " (- (* b%s (Math/log b%s)))" % %) 
                         n-range))
         (apply str (map #(format " (* b%s (Math/log p%s))" % %) 
                         n-range))         
         (apply str (map #(format " (- (* b%s (Math/log λ)))" %) 
                         n-range))
         )))


(defn effort-odd-quotient-numerator [n interim]
  (let [n-range (range 1 (inc n))]
    (str " (+"
         " (- (* k (Math/log a)))"
         (apply str (map #(format " (- (* b%s k (Math/log b%s)))" % %)
                        n-range))
         " (- (* c (Math/log c)))"
         " (* c (Math/log k))"
         (apply str (map #(format " (* b%s k (Math/log p%s))" % %)
                         n-range))
         " (* c (Math/log s))"
         " (- (* c (Math/log λ)))"
         (apply str (map #(format " (- (* b%s k (Math/log λ)))" %)
                         n-range))
         (if (zero? interim)
           ") "
           ")) ")
         (output-denominator n)
         )))


;; TODO: Rename
(defn handle-b-predecessor [top interim]
  (let [b-pred (if (zero? interim)
                   " (/"
                   (format " (- (/ (* b%s" interim))
        b-post (if (zero? interim)
                   ")"
                   "))")]
    (str b-pred
         (effort-odd-quotient-numerator top interim)
         b-post)))


(defn effort-space [n]
  "for n >= 2"
  (if (even? n)
    (str "(Math/pow Math/E (/ "
       (effort-even-numerator n)
       " "
       (output-denominator n)
       "))" )
    (str "(Math/pow Math/E (/ "
         "(+"
         (effort-odd-preamble n)
         (apply str (map (partial handle-b-predecessor n) (range (inc n))))
         ")"
         " c))")))

