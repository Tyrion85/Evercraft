(ns evercraft-tdd-kata.character-old-test
  (:require [expectations :refer :all]
            [evercraft-tdd-kata.character-old :refer :all]))

;; Given a string name should return new character with that name
(expect "Torn" ((create-character-person "Torn") :name))

;; Given a string name and Good alignment should return character with Good allignment
(expect "Good" ((create-character-person "Torn" "Good") :alignment))

;; Given a string name and Evil alignment should return character with Evil allignment
(expect "Evil" ((create-character-person "Torn" "Evil") :alignment))

;; Given a string name and Neutral alignment should return character with Neutral allignment
(expect "Neutral" ((create-character-person "Torn" "Neutral") :alignment))

;; Given a string name and unsupported alignment should return character with Neutral allignment
(expect "Neutral" ((create-character-person "Torn" "Chaotic") :alignment))

;; Setting an armor on a character should have this character with armor 10
(expect 10 ((set-armor(create-character-person "Torn" "Good")) :armor))

;; Setting an armor of 13 on a character should have this character with armor 13
(expect 13 ((set-armor(create-character-person "Torn" "Good") 13) :armor))

;; Setting an armor of 13 should return a character with his name
(expect "Torn" ((set-armor(create-character-person "Torn" "Good") 13) :name))

;; Setting an armor of 13 should return a character with his alignment
(expect "Good" ((set-armor(create-character-person "Torn" "Good") 13) :alignment))

;; Setting an armor of 13 should return a character with his alignment
(expect "Good" ((set-armor(create-character-person "Torn" "Good") 13) :alignment))

;; Setting hp on a char should return this char with hp 5
(expect 5 ((set-hp(set-armor(create-character-person "Torn" "Good") 13)) :hp))

;; Setting 11 hp on a char should return this char with hp 11
(expect 11 ((set-hp(set-armor(create-character-person "Torn" "Good") 13) 11) :hp))

;; Setting 11 hp on a char should return this char with his name
(expect "Torn" ((set-hp(set-armor(create-character-person "Torn" "Good") 13) 11) :name))

;; Setting 11 hp on a char should return this char with his alignment
(expect "Good" ((set-hp(set-armor(create-character-person "Torn" "Good") 13) 11) :alignment))

;; Setting 11 hp on a char should return this char with his armor class
(expect 13 ((set-hp(set-armor(create-character-person "Torn" "Good") 13) 11) :armor))

;;;;;;;;

(def torn
  (create-character "Torn" nil 13 8))

(def sarr
  (create-character "Sarr" "Evil" 11 9))

(def allister
  (create-character "Allister" "Good" 14 9))

(expect "Torn" (torn :name))
(expect "Neutral" (torn :alignment))
(expect 13 (torn :armor))
(expect 8 (torn :hp))

;; Rolling a number greater than opponent's armor class should hit him
(expect true (attack torn sarr 14))
(expect false (attack torn allister 11))



