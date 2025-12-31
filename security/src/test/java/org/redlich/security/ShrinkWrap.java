package org.redlich.security;

import static java.lang.System.getProperty;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;

import java.io.File;

import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public class ShrinkWrap {

    public static WebArchive mavenWar() {
        return
                create(ZipImporter.class, getProperty("finalName") + ".war")
                        .importFrom(new File("target/" + getProperty("finalName") + ".war"))
                        .as(WebArchive.class);
        }
    }
