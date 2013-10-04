# clojurecop

A Clojure library designed to collect code quality metrics for Clojure code.



# TODO

Pushback reader for line numbers

library for code walking. riddley

think about case when we can defined method inside the condition or defrecord?


can we track when method was changed significantly and docs were not updated?

# metrics


  * number of namespaces. May help you to understand how big library is. However maybe useless as LOC.
  * number of public methods
  * percantage of public methods with doctrings
  * find files that were changed a lot of times
  * calculate how many distict (use '...) were used in project
  * calculate number of anonymous functions
  * calculate number of macros
  * calculate number of types: defrecord?

  ...


# references

  * http://stackoverflow.com/questions/17622666/tool-like-metric-fu-or-codeclimate-com-for-clojure
  * http://stackoverflow.com/questions/5649559/specific-software-metrics-for-clojure-programs?rq=1
  * http://www.sonarqube.org/
  * https://codeclimate.com/


## License

Copyright © 2013 Hashobject Ltd (team@hashobject.com).

Distributed under the [Eclipse Public License](http://opensource.org/licenses/eclipse-1.0).
