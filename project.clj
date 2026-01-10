(def kitchensink-version "3.5.5")
(def trapperkeeper-version "4.3.2")
(def i18n-version "1.0.3")

(defproject org.openvoxproject/trapperkeeper-scheduler "1.3.1"
  :description "Trapperkeeper Scheduler Service"

  ;; These are to enforce consistent versions across dependencies of dependencies,
  ;; and to avoid having to define versions in multiple places. If a component
  ;; defined under :dependencies ends up causing an error due to :pedantic? :abort,
  ;; because it is a dep of a dep with a different version, move it here.
  :managed-dependencies [[org.clojure/clojure "1.12.4"]
                         [org.clojure/tools.logging "1.3.1"]
                         [clj-time "0.15.2"]

                         [org.openvoxproject/kitchensink ~kitchensink-version]
                         [org.openvoxproject/kitchensink ~kitchensink-version :classifier "test"]
                         [org.openvoxproject/trapperkeeper ~trapperkeeper-version]
                         [org.openvoxproject/trapperkeeper ~trapperkeeper-version :classifier "test"]]

  :dependencies [[org.clojure/clojure]
                 [org.openvoxproject/trapperkeeper]
                 [org.openvoxproject/i18n ~i18n-version]
                 [org.openvoxproject/kitchensink]
                 [org.quartz-scheduler/quartz "2.5.2" :exclusions [c3p0]]]

  :min-lein-version "2.9.1"

  :pedantic? :abort

  :license {:name "Apache License, Version 2.0" 
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}

  :test-paths ["test/unit" "test/integration"]

  :test-selectors {:integration :integration
                   :unit (complement :integration)}

  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/CLOJARS_USERNAME
                                     :password :env/CLOJARS_PASSWORD
                                     :sign-releases false}]]

  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.openvoxproject/trapperkeeper :classifier "test" :scope "test"]
                                  [org.openvoxproject/kitchensink :classifier "test" :scope "test"]]}}

  :plugins  [[org.openvoxproject/i18n ~i18n-version]]
  :aot [puppetlabs.trapperkeeper.services.scheduler.job]
  :repl-options {:init-ns user})
