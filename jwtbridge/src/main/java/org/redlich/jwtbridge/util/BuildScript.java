package org.redlich.jwtbridge.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildScript {

    private String token;

    public BuildScript(String token) {
        setToken(token);
        }

    public static void main(String[] args) {
        }

    public String getToken() {
        return token;
        }

    public void setToken(String token) {
        this.token = token;
        }

    public void writeToFile(String token) {
        Path filePath = Paths.get("run.sh");
        List<String> lines = List.of("#!/bin/zsh\n", "curl -X GET http://localhost:8080/jwtbridge-1.0.0/api/secured/hi-with-token -H \"Authorization: Bearer " + token + "\"\n");

        try {
            // Write lines to the file (overwrites if file exists)
            Files.write(filePath, lines);

            Set<PosixFilePermission> permissions = new HashSet<>();
            permissions.add(PosixFilePermission.OWNER_READ);
            permissions.add(PosixFilePermission.OWNER_WRITE);
            permissions.add(PosixFilePermission.OWNER_EXECUTE);
            permissions.add(PosixFilePermission.GROUP_READ);
            permissions.add(PosixFilePermission.GROUP_EXECUTE);
            permissions.add(PosixFilePermission.OTHERS_READ);
            permissions.add(PosixFilePermission.OTHERS_EXECUTE);

            Files.setPosixFilePermissions(filePath, permissions);
            }
        catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            }
        }
    }
