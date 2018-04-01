/*
 * This file is part of QuickStart Module Loader - FCS, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package uk.co.drnaylor.quickstart.fcs;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import uk.co.drnaylor.quickstart.modulecontainers.discoverystrategies.Strategy;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FastClasspathScannerStrategy implements Strategy {

    @Override
    public Set<Class<?>> discover(String topPackage, ClassLoader classLoader) throws Exception {
        ScanResult scanResult = new FastClasspathScanner(topPackage).scan();
        return new HashSet<>(scanResult.classNamesToClassRefs(scanResult.getNamesOfAllClasses().stream()
                .filter(x -> x.startsWith(topPackage))
                .collect(Collectors.toList())));
    }

}
