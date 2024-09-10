/*
 * MATLAB Compiler: 8.4 (R2022a)
 * Date: Mon Sep  9 21:45:40 2024
 * Arguments: 
 * "-B""macro_default""-W""java:STRS,STRSSignature""-T""link:lib""-d""D:\\demo\\STRS\\for_testing""class{STRSSignature:E:\\STRSDEmo\\hash.m,E:\\STRSDEmo\\keygen.m,E:\\STRSDEmo\\setup.m,E:\\STRSDEmo\\signature.m,E:\\STRSDEmo\\verify.m}"
 */

package STRS;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;
import java.io.Serializable;
/**
 * <i>INTERNAL USE ONLY</i>
 */
public class STRSMCRFactory implements Serializable 
{
    /** Component's uuid */
    private static final String sComponentId = "STRS_497425d7-2812-4a35-88f3-f1ec8092352a";
    
    /** Component name */
    private static final String sComponentName = "STRS";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(STRSMCRFactory.class)
        );
    
    
    private STRSMCRFactory()
    {
        // Never called.
    }
    
    public static MWMCR newInstance(MWComponentOptions componentOptions) throws MWException
    {
        if (null == componentOptions.getCtfSource()) {
            componentOptions = new MWComponentOptions(componentOptions);
            componentOptions.setCtfSource(sDefaultComponentOptions.getCtfSource());
        }
        return MWMCR.newInstance(
            componentOptions, 
            STRSMCRFactory.class, 
            sComponentName, 
            sComponentId,
            new int[]{9,12,0}
        );
    }
    
    public static MWMCR newInstance() throws MWException
    {
        return newInstance(sDefaultComponentOptions);
    }
}
