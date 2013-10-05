(ns clojurecop.test)

(defprotocol Fly
  "A simple protocol for flying"
  (fly [this] "Method to fly"))



(defprotocol Test
  (test-me [this] "Method to testing"))


(deftype Person [name]
  Test)


(defprotocol IAnimal
  "the animal protocol"
  (inner-report [o] "a report"))

;(defrecord Dog []
;  IAnimal
;  (inner-report [o]
;    "Woof Woof"))

(defn test-fun-without-doc []
  "xx")

(defn test-fun-with-doc
  "some function with doc"
  []
  "xx")


(def pi 3.14)


;(inner-report (Dog.))
