package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibsInPluginsBlock extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final CommonsLibraryAccessors laccForCommonsLibraryAccessors = new CommonsLibraryAccessors(owner);
    private final JavaxLibraryAccessors laccForJavaxLibraryAccessors = new JavaxLibraryAccessors(owner);
    private final JunitLibraryAccessors laccForJunitLibraryAccessors = new JunitLibraryAccessors(owner);
    private final MysqlLibraryAccessors laccForMysqlLibraryAccessors = new MysqlLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibsInPluginsBlock(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of libraries at com
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public ComLibraryAccessors getCom() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForComLibraryAccessors;
    }

    /**
     * Returns the group of libraries at commons
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public CommonsLibraryAccessors getCommons() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForCommonsLibraryAccessors;
    }

    /**
     * Returns the group of libraries at javax
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public JavaxLibraryAccessors getJavax() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForJavaxLibraryAccessors;
    }

    /**
     * Returns the group of libraries at junit
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public JunitLibraryAccessors getJunit() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForJunitLibraryAccessors;
    }

    /**
     * Returns the group of libraries at mysql
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public MysqlLibraryAccessors getMysql() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForMysqlLibraryAccessors;
    }

    /**
     * Returns the group of libraries at org
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public OrgLibraryAccessors getOrg() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForOrgLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public BundleAccessors getBundles() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleLibraryAccessors laccForComGoogleLibraryAccessors = new ComGoogleLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComGoogleLibraryAccessors getGoogle() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComGoogleLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComGoogleLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeLibraryAccessors laccForComGoogleCodeLibraryAccessors = new ComGoogleCodeLibraryAccessors(owner);

        public ComGoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.code
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComGoogleCodeLibraryAccessors getCode() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComGoogleCodeLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComGoogleCodeLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeGsonLibraryAccessors laccForComGoogleCodeGsonLibraryAccessors = new ComGoogleCodeGsonLibraryAccessors(owner);

        public ComGoogleCodeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.code.gson
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public ComGoogleCodeGsonLibraryAccessors getGson() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForComGoogleCodeGsonLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class ComGoogleCodeGsonLibraryAccessors extends SubDependencyFactory {

        public ComGoogleCodeGsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for gson (com.google.code.gson:gson)
         * with versionRef 'com.google.code.gson.gson'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getGson() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("com.google.code.gson.gson");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class CommonsLibraryAccessors extends SubDependencyFactory {
        private final CommonsFileuploadLibraryAccessors laccForCommonsFileuploadLibraryAccessors = new CommonsFileuploadLibraryAccessors(owner);
        private final CommonsIoLibraryAccessors laccForCommonsIoLibraryAccessors = new CommonsIoLibraryAccessors(owner);

        public CommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at commons.fileupload
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public CommonsFileuploadLibraryAccessors getFileupload() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForCommonsFileuploadLibraryAccessors;
        }

        /**
         * Returns the group of libraries at commons.io
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public CommonsIoLibraryAccessors getIo() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForCommonsIoLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class CommonsFileuploadLibraryAccessors extends SubDependencyFactory {
        private final CommonsFileuploadCommonsLibraryAccessors laccForCommonsFileuploadCommonsLibraryAccessors = new CommonsFileuploadCommonsLibraryAccessors(owner);

        public CommonsFileuploadLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at commons.fileupload.commons
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public CommonsFileuploadCommonsLibraryAccessors getCommons() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForCommonsFileuploadCommonsLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class CommonsFileuploadCommonsLibraryAccessors extends SubDependencyFactory {

        public CommonsFileuploadCommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for fileupload (commons-fileupload:commons-fileupload)
         * with versionRef 'commons.fileupload.commons.fileupload'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getFileupload() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("commons.fileupload.commons.fileupload");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class CommonsIoLibraryAccessors extends SubDependencyFactory {
        private final CommonsIoCommonsLibraryAccessors laccForCommonsIoCommonsLibraryAccessors = new CommonsIoCommonsLibraryAccessors(owner);

        public CommonsIoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at commons.io.commons
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public CommonsIoCommonsLibraryAccessors getCommons() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForCommonsIoCommonsLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class CommonsIoCommonsLibraryAccessors extends SubDependencyFactory {

        public CommonsIoCommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for io (commons-io:commons-io)
         * with versionRef 'commons.io.commons.io'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getIo() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("commons.io.commons.io");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxMailLibraryAccessors laccForJavaxMailLibraryAccessors = new JavaxMailLibraryAccessors(owner);
        private final JavaxServletLibraryAccessors laccForJavaxServletLibraryAccessors = new JavaxServletLibraryAccessors(owner);

        public JavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at javax.mail
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public JavaxMailLibraryAccessors getMail() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForJavaxMailLibraryAccessors;
        }

        /**
         * Returns the group of libraries at javax.servlet
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public JavaxServletLibraryAccessors getServlet() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForJavaxServletLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JavaxMailLibraryAccessors extends SubDependencyFactory {
        private final JavaxMailJavaxLibraryAccessors laccForJavaxMailJavaxLibraryAccessors = new JavaxMailJavaxLibraryAccessors(owner);

        public JavaxMailLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for mail (javax.mail:mail)
         * with versionRef 'javax.mail.mail'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getMail() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("javax.mail.mail");
        }

        /**
         * Returns the group of libraries at javax.mail.javax
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public JavaxMailJavaxLibraryAccessors getJavax() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForJavaxMailJavaxLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JavaxMailJavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxMailJavaxMailLibraryAccessors laccForJavaxMailJavaxMailLibraryAccessors = new JavaxMailJavaxMailLibraryAccessors(owner);

        public JavaxMailJavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at javax.mail.javax.mail
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public JavaxMailJavaxMailLibraryAccessors getMail() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForJavaxMailJavaxMailLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JavaxMailJavaxMailLibraryAccessors extends SubDependencyFactory {

        public JavaxMailJavaxMailLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (javax.mail:javax.mail-api)
         * with versionRef 'javax.mail.javax.mail.api'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getApi() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("javax.mail.javax.mail.api");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JavaxServletLibraryAccessors extends SubDependencyFactory {
        private final JavaxServletJavaxLibraryAccessors laccForJavaxServletJavaxLibraryAccessors = new JavaxServletJavaxLibraryAccessors(owner);

        public JavaxServletLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at javax.servlet.javax
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public JavaxServletJavaxLibraryAccessors getJavax() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForJavaxServletJavaxLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JavaxServletJavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxServletJavaxServletLibraryAccessors laccForJavaxServletJavaxServletLibraryAccessors = new JavaxServletJavaxServletLibraryAccessors(owner);

        public JavaxServletJavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at javax.servlet.javax.servlet
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public JavaxServletJavaxServletLibraryAccessors getServlet() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForJavaxServletJavaxServletLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JavaxServletJavaxServletLibraryAccessors extends SubDependencyFactory {

        public JavaxServletJavaxServletLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (javax.servlet:javax.servlet-api)
         * with versionRef 'javax.servlet.javax.servlet.api'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getApi() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("javax.servlet.javax.servlet.api");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class JunitLibraryAccessors extends SubDependencyFactory {

        public JunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (junit:junit)
         * with versionRef 'junit.junit'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getJunit() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("junit.junit");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class MysqlLibraryAccessors extends SubDependencyFactory {
        private final MysqlMysqlLibraryAccessors laccForMysqlMysqlLibraryAccessors = new MysqlMysqlLibraryAccessors(owner);

        public MysqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at mysql.mysql
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public MysqlMysqlLibraryAccessors getMysql() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForMysqlMysqlLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class MysqlMysqlLibraryAccessors extends SubDependencyFactory {
        private final MysqlMysqlConnectorLibraryAccessors laccForMysqlMysqlConnectorLibraryAccessors = new MysqlMysqlConnectorLibraryAccessors(owner);

        public MysqlMysqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at mysql.mysql.connector
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public MysqlMysqlConnectorLibraryAccessors getConnector() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForMysqlMysqlConnectorLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class MysqlMysqlConnectorLibraryAccessors extends SubDependencyFactory {

        public MysqlMysqlConnectorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (mysql:mysql-connector-java)
         * with versionRef 'mysql.mysql.connector.java'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getJava() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("mysql.mysql.connector.java");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgBouncycastleLibraryAccessors laccForOrgBouncycastleLibraryAccessors = new OrgBouncycastleLibraryAccessors(owner);
        private final OrgJdbiLibraryAccessors laccForOrgJdbiLibraryAccessors = new OrgJdbiLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.bouncycastle
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgBouncycastleLibraryAccessors getBouncycastle() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgBouncycastleLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.jdbi
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgJdbiLibraryAccessors getJdbi() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgJdbiLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgBouncycastleLibraryAccessors extends SubDependencyFactory {
        private final OrgBouncycastleBcpkixLibraryAccessors laccForOrgBouncycastleBcpkixLibraryAccessors = new OrgBouncycastleBcpkixLibraryAccessors(owner);

        public OrgBouncycastleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.bouncycastle.bcpkix
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgBouncycastleBcpkixLibraryAccessors getBcpkix() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgBouncycastleBcpkixLibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgBouncycastleBcpkixLibraryAccessors extends SubDependencyFactory {

        public OrgBouncycastleBcpkixLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jdk18on (org.bouncycastle:bcpkix-jdk18on)
         * with versionRef 'org.bouncycastle.bcpkix.jdk18on'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getJdk18on() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("org.bouncycastle.bcpkix.jdk18on");
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgJdbiLibraryAccessors extends SubDependencyFactory {
        private final OrgJdbiJdbi3LibraryAccessors laccForOrgJdbiJdbi3LibraryAccessors = new OrgJdbiJdbi3LibraryAccessors(owner);

        public OrgJdbiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jdbi.jdbi3
         * @deprecated Will be removed in Gradle 9.0.
         */
        @Deprecated
        public OrgJdbiJdbi3LibraryAccessors getJdbi3() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return laccForOrgJdbiJdbi3LibraryAccessors;
        }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class OrgJdbiJdbi3LibraryAccessors extends SubDependencyFactory {

        public OrgJdbiJdbi3LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.jdbi:jdbi3-core)
         * with versionRef 'org.jdbi.jdbi3.core'.
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getCore() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("org.jdbi.jdbi3.core");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final CommonsVersionAccessors vaccForCommonsVersionAccessors = new CommonsVersionAccessors(providers, config);
        private final JavaxVersionAccessors vaccForJavaxVersionAccessors = new JavaxVersionAccessors(providers, config);
        private final JunitVersionAccessors vaccForJunitVersionAccessors = new JunitVersionAccessors(providers, config);
        private final MysqlVersionAccessors vaccForMysqlVersionAccessors = new MysqlVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.commons
         */
        public CommonsVersionAccessors getCommons() {
            return vaccForCommonsVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.javax
         */
        public JavaxVersionAccessors getJavax() {
            return vaccForJavaxVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.junit
         */
        public JunitVersionAccessors getJunit() {
            return vaccForJunitVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.mysql
         */
        public MysqlVersionAccessors getMysql() {
            return vaccForMysqlVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComGoogleVersionAccessors vaccForComGoogleVersionAccessors = new ComGoogleVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.google
         */
        public ComGoogleVersionAccessors getGoogle() {
            return vaccForComGoogleVersionAccessors;
        }

    }

    public static class ComGoogleVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeVersionAccessors vaccForComGoogleCodeVersionAccessors = new ComGoogleCodeVersionAccessors(providers, config);
        public ComGoogleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.google.code
         */
        public ComGoogleCodeVersionAccessors getCode() {
            return vaccForComGoogleCodeVersionAccessors;
        }

    }

    public static class ComGoogleCodeVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeGsonVersionAccessors vaccForComGoogleCodeGsonVersionAccessors = new ComGoogleCodeGsonVersionAccessors(providers, config);
        public ComGoogleCodeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.google.code.gson
         */
        public ComGoogleCodeGsonVersionAccessors getGson() {
            return vaccForComGoogleCodeGsonVersionAccessors;
        }

    }

    public static class ComGoogleCodeGsonVersionAccessors extends VersionFactory  {

        public ComGoogleCodeGsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.google.code.gson.gson (2.8.9)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGson() { return getVersion("com.google.code.gson.gson"); }

    }

    public static class CommonsVersionAccessors extends VersionFactory  {

        private final CommonsFileuploadVersionAccessors vaccForCommonsFileuploadVersionAccessors = new CommonsFileuploadVersionAccessors(providers, config);
        private final CommonsIoVersionAccessors vaccForCommonsIoVersionAccessors = new CommonsIoVersionAccessors(providers, config);
        public CommonsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.commons.fileupload
         */
        public CommonsFileuploadVersionAccessors getFileupload() {
            return vaccForCommonsFileuploadVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.commons.io
         */
        public CommonsIoVersionAccessors getIo() {
            return vaccForCommonsIoVersionAccessors;
        }

    }

    public static class CommonsFileuploadVersionAccessors extends VersionFactory  {

        private final CommonsFileuploadCommonsVersionAccessors vaccForCommonsFileuploadCommonsVersionAccessors = new CommonsFileuploadCommonsVersionAccessors(providers, config);
        public CommonsFileuploadVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.commons.fileupload.commons
         */
        public CommonsFileuploadCommonsVersionAccessors getCommons() {
            return vaccForCommonsFileuploadCommonsVersionAccessors;
        }

    }

    public static class CommonsFileuploadCommonsVersionAccessors extends VersionFactory  {

        public CommonsFileuploadCommonsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: commons.fileupload.commons.fileupload (1.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFileupload() { return getVersion("commons.fileupload.commons.fileupload"); }

    }

    public static class CommonsIoVersionAccessors extends VersionFactory  {

        private final CommonsIoCommonsVersionAccessors vaccForCommonsIoCommonsVersionAccessors = new CommonsIoCommonsVersionAccessors(providers, config);
        public CommonsIoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.commons.io.commons
         */
        public CommonsIoCommonsVersionAccessors getCommons() {
            return vaccForCommonsIoCommonsVersionAccessors;
        }

    }

    public static class CommonsIoCommonsVersionAccessors extends VersionFactory  {

        public CommonsIoCommonsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: commons.io.commons.io (2.11.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getIo() { return getVersion("commons.io.commons.io"); }

    }

    public static class JavaxVersionAccessors extends VersionFactory  {

        private final JavaxMailVersionAccessors vaccForJavaxMailVersionAccessors = new JavaxMailVersionAccessors(providers, config);
        private final JavaxServletVersionAccessors vaccForJavaxServletVersionAccessors = new JavaxServletVersionAccessors(providers, config);
        public JavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.javax.mail
         */
        public JavaxMailVersionAccessors getMail() {
            return vaccForJavaxMailVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.javax.servlet
         */
        public JavaxServletVersionAccessors getServlet() {
            return vaccForJavaxServletVersionAccessors;
        }

    }

    public static class JavaxMailVersionAccessors extends VersionFactory  {

        private final JavaxMailJavaxVersionAccessors vaccForJavaxMailJavaxVersionAccessors = new JavaxMailJavaxVersionAccessors(providers, config);
        public JavaxMailVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: javax.mail.mail (1.4.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMail() { return getVersion("javax.mail.mail"); }

        /**
         * Returns the group of versions at versions.javax.mail.javax
         */
        public JavaxMailJavaxVersionAccessors getJavax() {
            return vaccForJavaxMailJavaxVersionAccessors;
        }

    }

    public static class JavaxMailJavaxVersionAccessors extends VersionFactory  {

        private final JavaxMailJavaxMailVersionAccessors vaccForJavaxMailJavaxMailVersionAccessors = new JavaxMailJavaxMailVersionAccessors(providers, config);
        public JavaxMailJavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.javax.mail.javax.mail
         */
        public JavaxMailJavaxMailVersionAccessors getMail() {
            return vaccForJavaxMailJavaxMailVersionAccessors;
        }

    }

    public static class JavaxMailJavaxMailVersionAccessors extends VersionFactory  {

        public JavaxMailJavaxMailVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: javax.mail.javax.mail.api (1.5.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getApi() { return getVersion("javax.mail.javax.mail.api"); }

    }

    public static class JavaxServletVersionAccessors extends VersionFactory  {

        private final JavaxServletJavaxVersionAccessors vaccForJavaxServletJavaxVersionAccessors = new JavaxServletJavaxVersionAccessors(providers, config);
        public JavaxServletVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.javax.servlet.javax
         */
        public JavaxServletJavaxVersionAccessors getJavax() {
            return vaccForJavaxServletJavaxVersionAccessors;
        }

    }

    public static class JavaxServletJavaxVersionAccessors extends VersionFactory  {

        private final JavaxServletJavaxServletVersionAccessors vaccForJavaxServletJavaxServletVersionAccessors = new JavaxServletJavaxServletVersionAccessors(providers, config);
        public JavaxServletJavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.javax.servlet.javax.servlet
         */
        public JavaxServletJavaxServletVersionAccessors getServlet() {
            return vaccForJavaxServletJavaxServletVersionAccessors;
        }

    }

    public static class JavaxServletJavaxServletVersionAccessors extends VersionFactory  {

        public JavaxServletJavaxServletVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: javax.servlet.javax.servlet.api (3.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getApi() { return getVersion("javax.servlet.javax.servlet.api"); }

    }

    public static class JunitVersionAccessors extends VersionFactory  {

        public JunitVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: junit.junit (3.8.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit.junit"); }

    }

    public static class MysqlVersionAccessors extends VersionFactory  {

        private final MysqlMysqlVersionAccessors vaccForMysqlMysqlVersionAccessors = new MysqlMysqlVersionAccessors(providers, config);
        public MysqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.mysql.mysql
         */
        public MysqlMysqlVersionAccessors getMysql() {
            return vaccForMysqlMysqlVersionAccessors;
        }

    }

    public static class MysqlMysqlVersionAccessors extends VersionFactory  {

        private final MysqlMysqlConnectorVersionAccessors vaccForMysqlMysqlConnectorVersionAccessors = new MysqlMysqlConnectorVersionAccessors(providers, config);
        public MysqlMysqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.mysql.mysql.connector
         */
        public MysqlMysqlConnectorVersionAccessors getConnector() {
            return vaccForMysqlMysqlConnectorVersionAccessors;
        }

    }

    public static class MysqlMysqlConnectorVersionAccessors extends VersionFactory  {

        public MysqlMysqlConnectorVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: mysql.mysql.connector.java (8.0.28)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJava() { return getVersion("mysql.mysql.connector.java"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgBouncycastleVersionAccessors vaccForOrgBouncycastleVersionAccessors = new OrgBouncycastleVersionAccessors(providers, config);
        private final OrgJdbiVersionAccessors vaccForOrgJdbiVersionAccessors = new OrgJdbiVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.bouncycastle
         */
        public OrgBouncycastleVersionAccessors getBouncycastle() {
            return vaccForOrgBouncycastleVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.jdbi
         */
        public OrgJdbiVersionAccessors getJdbi() {
            return vaccForOrgJdbiVersionAccessors;
        }

    }

    public static class OrgBouncycastleVersionAccessors extends VersionFactory  {

        private final OrgBouncycastleBcpkixVersionAccessors vaccForOrgBouncycastleBcpkixVersionAccessors = new OrgBouncycastleBcpkixVersionAccessors(providers, config);
        public OrgBouncycastleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.bouncycastle.bcpkix
         */
        public OrgBouncycastleBcpkixVersionAccessors getBcpkix() {
            return vaccForOrgBouncycastleBcpkixVersionAccessors;
        }

    }

    public static class OrgBouncycastleBcpkixVersionAccessors extends VersionFactory  {

        public OrgBouncycastleBcpkixVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.bouncycastle.bcpkix.jdk18on (1.76)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJdk18on() { return getVersion("org.bouncycastle.bcpkix.jdk18on"); }

    }

    public static class OrgJdbiVersionAccessors extends VersionFactory  {

        private final OrgJdbiJdbi3VersionAccessors vaccForOrgJdbiJdbi3VersionAccessors = new OrgJdbiJdbi3VersionAccessors(providers, config);
        public OrgJdbiVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.jdbi.jdbi3
         */
        public OrgJdbiJdbi3VersionAccessors getJdbi3() {
            return vaccForOrgJdbiJdbi3VersionAccessors;
        }

    }

    public static class OrgJdbiJdbi3VersionAccessors extends VersionFactory  {

        public OrgJdbiJdbi3VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.jdbi.jdbi3.core (3.24.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("org.jdbi.jdbi3.core"); }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
