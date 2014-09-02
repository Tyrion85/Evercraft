(ns evercraft-tdd-kata.character)

(defn- merge-character-attribute [character new-attributes-map]
  (merge (deref character) new-attributes-map))

(defn- set-character-attribute [character attribute-name attribute-value]
  (dosync
  (ref-set character
   (merge-character-attribute
    character
    {(keyword attribute-name) attribute-value}))))

(defn- set-character-attributes [character attributes]
  (reduce #(set-character-attribute character %1 %2) attributes))

(defn set-name [character name]
  (set-character-attribute character "name" name))

(defn set-alignment [character alignment]
  (let [good? (= alignment "Good")
        evil? (= alignment "Evil")
        neutral? (= alignment "Neutral")
        align (cond
               good? "Good"
               evil? "Evil"
               neutral? "Neutral"
               :else "Neutral")]
    (set-character-attribute character "alignment" align)))

(defn set-armor [character armor]
  (let [default-armor 10
        actual-armor (if (nil? armor) default-armor armor)]
    (set-character-attribute character "armor" actual-armor)))

(defn set-hp [character hp]
  (let [default-hp 5
        actual-hp (if (nil? hp) default-hp hp)]
    (set-character-attribute character "hp" actual-hp)))

(defn create-character [name]
  (ref {:name name}))

(defn hit [character dice-roll]
  (>= dice-roll ((deref character) :armor)))

(defn attack [character dice-roll]
  (let [critical? (= 20 dice-roll)
        hit? (hit character dice-roll)
        hp ((deref character) :hp)
        damage (if critical? 2 1)
        hp-after-damage (- hp damage)
        dead? (<= hp-after-damage 0)]
    (if hit?
      (if dead?
        (set-character-attributes
         character
         [["dead" true] ["hp" hp-after-damage]])
       (set-character-attribute character "hp" hp-after-damage))
    character)))





