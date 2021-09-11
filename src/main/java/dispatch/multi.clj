(definterface Shape)

(defrecord    Circle [^double radius]                Shape)

(defrecord    Rect   [^double width, ^double height] Shape)


(defmulti method
  (fn [^Shape a, ^Shape b]
    [(type a) (type b)]))


(defmethod method [Circle Circle]
  ([a b] "Circle/Circle"))

(defmethod method [Circle Rect  ]
  ([a b] "Circle/Rect"))

(defmethod method [Rect   Circle]
  ([a b] "Rect/Circle"))

(defmethod method [Rect   Rect  ]
  ([a b] "Rect/Rect"))


(let [^Shape circle (Circle. 1)
      ^Shape rect   (Rect. 2 3)]
  (method circle rect))
