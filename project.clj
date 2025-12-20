(defproject org.openvoxproject/trapperkeeper-scheduler "1.2.4-SNAPSHOT"
  :description "Trapperkeeper Scheduler Service"

  :dependencies [[org.clojure/clojure]
                 [org.openvoxproject/trapperkeeper]
                 [org.openvoxproject/i18n]
                 [org.openvoxproject/kitchensink]
                 [org.quartz-scheduler/quartz "2.3.2" :exclusions [c3p0]]]

  :min-lein-version "2.9.1"

  :parent-project {:coords [org.openvoxproject/clj-parent "7.4.1-SNAPSHOT"]
                   :inherit [:managed-dependencies]}

  :pedantic? :abort

  :license {:name "Apache License, Version 2.0" 
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}

  :test-paths ["test/unit" "test/integration"]

  :test-selectors {:integration :integration
                   :unit (complement :integration)}

  :deploy-repositories [["releases" {:url "https://clojars.org/repo"
                                     :username :env/clojars_jenkins_username
                                     :password :env/clojars_jenkins_password
                                     :sign-releases false}]]

  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.openvoxproject/trapperkeeper :classifier "test" :scope "test"]
                                  [org.openvoxproject/kitchensink :classifier "test" :scope "test"]]}}

  :plugins  [[lein-parent "0.3.7"]
             [org.openvoxproject/i18n "0.8.0"]]
  :aot [puppetlabs.trapperkeeper.services.scheduler.job]
  :repl-options {:init-ns user})
