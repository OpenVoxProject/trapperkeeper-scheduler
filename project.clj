(def i18n-version "1.0.3")

(defproject org.openvoxproject/trapperkeeper-scheduler "1.3.2-SNAPSHOT"
  :description "Trapperkeeper Scheduler Service"

  ;; Generally, try to keep version pins in :managed-dependencies and the libraries
  ;; this project actually uses in :dependencies, inheriting the version from
  ;; :managed-dependencies. This prevents endless version conflicts due to deps of deps.
  ;; Renovate should keep the versions largely in sync between projects.
  :managed-dependencies [[org.clojure/clojure "1.12.4"]
                         [org.clojure/tools.logging "1.3.1"]
                         [clj-time "0.15.2"]
                         [org.openvoxproject/i18n ~i18n-version]
                         [org.openvoxproject/kitchensink "3.5.6"]
                         [org.openvoxproject/kitchensink "3.5.6" :classifier "test"]
                         [org.openvoxproject/trapperkeeper "4.3.4"]
                         [org.openvoxproject/trapperkeeper "4.3.4" :classifier "test"]
                         [org.quartz-scheduler/quartz "2.5.2"]]

  :dependencies [[org.clojure/clojure]
                 [org.openvoxproject/i18n]
                 [org.openvoxproject/kitchensink]
                 [org.openvoxproject/trapperkeeper]
                 [org.quartz-scheduler/quartz :exclusions [c3p0]]]

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
