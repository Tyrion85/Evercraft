(ns evercraft-tdd-kata.character-old)

(defn create-character-person [name & alignment]
  (let [good? (= (first alignment) "Good")
        evil? (= (first alignment) "Evil")
        neutral? (= (first alignment) "Neutral")
        align (cond
               good? "Good"
               evil? "Evil"
               neutral? "Neutral"
               :else "Neutral")]
  {:name name
   :alignment align
   }))

(defn set-armor [character & args]
  (let [armor (first args)
        default-armor 10]
    (merge
     {:armor
      (if (nil? armor) default-armor armor)}
     character
     )))

(defn set-hp [character & args]
  (let [hp (first args)
        default-hp 5]
   (merge
   {:hp (if (nil? hp) default-hp hp)}
   character)))


(defn create-character [name alignment armor hp]
  (set-hp (set-armor (create-character-person name alignment) armor) hp))

(defn attack [attacker defender roll]
  (>= roll (defender :armor)))


;;;; Experimental - refs
;; Sets a ref to character
(def c1 (ref {:name "Tho" :hp 13}))

;; Defines attack function
(defn att [hp hit]
  (- hp hit))

;; Defines attack-character function
(defn att-char [character hit]
  (merge
   character
   {:hp (att (character :hp) hit)}
   ))

;; Attack reference! :)
(dosync
 (ref-set
  c1 (att-char (deref c1) 3)))

;; Define function for attacking character that is a ref!!!
(defn atta [character hit]
  (dosync
   (ref-set character (att-char (deref character) hit))))

;; Create a character-ref :)
(def c2 (ref {:name "Thor" :hp 14}))
;; Attack him! :)
(atta c2 3)
;; See the result! ^_^
((deref c2) :hp)

(dosync
 (ref-set
  c1
  {:name (c1 :name) :hp (att (c1 :hp) 4)}))

(dosync
 (ref-set
  c1
  (merge (deref c1) {:hp (att (c1 :hp) 4)})))

(c1 :hp)
(c1 :name)

