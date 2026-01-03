(defproject org.openvoxproject/trapperkeeper-scheduler "1.3.0"
  :description "Trapperkeeper Scheduler Service"

  :dependencies [[org.clojure/clojure]
                 [org.openvoxproject/trapperkeeper]
                 [org.openvoxproject/i18n]
                 [org.openvoxproject/kitchensink]
                 [org.quartz-scheduler/quartz "2.3.2" :exclusions [c3p0]]]

  :min-lein-version "2.9.1"

  :parent-project {:coords [org.openvoxproject/clj-parent "7.6.3"]
                   :inherit [:managed-dependencies]}

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

  :plugins  [[lein-parent "0.3.9"]
             [org.openvoxproject/i18n "1.0.2"]]
  :aot [puppetlabs.trapperkeeper.services.scheduler.job]
  :repl-options {:init-ns user})
