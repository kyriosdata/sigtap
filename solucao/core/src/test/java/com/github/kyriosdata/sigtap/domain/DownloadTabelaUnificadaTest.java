/*
 * Copyright (c) 2016.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.sigtap.domain;

import com.github.kyriosdata.sigtap.DownloadTabelaUnificada;
import org.assertj.core.util.Files;
import org.junit.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadTabelaUnificadaTest {

    @Test
    public void competenciaInvalida() {
        assertNull(DownloadTabelaUnificada.baixar("203", "dir"));
    }

    @Test
    public void competenciaExistente() {
        String dir = Files.temporaryFolderPath();
        File arquivo = DownloadTabelaUnificada.baixar("202003", dir);
        assertTrue(arquivo.getName().contains("_202003_"));
    }
}


