(ns evercraft-tdd-kata.character-test
  (:require [expectations :refer :all]
            [evercraft-tdd-kata.character :refer :all]))

(expect "Thir" ((deref (create-character "Thir")) :name))

(def Thor (create-character "Th"))

;; Given a reference to character, his name should be available
(expect "Th" (Thor :name))

;; Given a character, his name should be changeable
(expect "Thor" ((set-name Thor "Thor") :name))
(expect "Thor" (Thor :name))

;; Given an empty alignment string, character should have neutral alignment
(expect "Neutral" ((set-alignment Thor nil) :alignment))

;; Given a "Neutral" alignment string, character should have Neutral alignment
(expect "Neutral" ((set-alignment Thor "Neutral") :alignment))

;; Given an "Evil" alignment string, character should have Evil alignment
(expect "Evil" ((set-alignment Thor "Evil") :alignment))

;; Given a "Good" alignment string, character should have Good alignment
(expect "Good" ((set-alignment Thor "Good") :alignment))

;; Given a nil for armor should set this character's armor to 10
(expect 10 ((set-armor Thor nil) :armor))

;; Given an integer of 13 for armor should set this character's armor to 13
(expect 13 ((set-armor Thor 13) :armor))

;; Given a nil for hp should have this character's hp set to 5
(expect 5 ((set-hp Thor nil) :hp))

;; Given an integer of 8 for hp should have this character's hp set to 8
(expect 8 ((set-hp Thor 8) :hp))

;; Hitting a character with roll greater than his armor should hit him
(expect true (hit Thor 14))

;; Hitting a character with roll lower than his armor should not hit him
(expect false (hit Thor 12))

;; Hitting a character with roll equal to his armor should hit him
(expect true (hit Thor 13))

;; Attacking a char and missing a hit should result in character having the same
;; amount of hp as before he was attacked
(expect 8 ((attack Thor 12) :hp))

;; Successful hit should lower character's hit point count by one
(expect 7 ((attack Thor 14) :hp))

;; Critical hit (roll of 20) should result in double damage
(expect 5 ((attack Thor 20) :hp))

;; When character's hit points reach 0, he should be dead
(expect 3 ((attack Thor 20) :hp))
(expect 1 ((attack Thor 20) :hp))
(expect true ((attack Thor 13) :dead))
(expect 0 (Thor :hp))
(expect true (Thor :dead))

