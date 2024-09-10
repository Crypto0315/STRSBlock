/*
 * MATLAB Compiler: 8.4 (R2022a)
 * Date: Mon Sep  9 21:45:40 2024
 * Arguments: 
 * "-B""macro_default""-W""java:STRS,STRSSignature""-T""link:lib""-d""D:\\demo\\STRS\\for_testing""class{STRSSignature:E:\\STRSDEmo\\hash.m,E:\\STRSDEmo\\keygen.m,E:\\STRSDEmo\\setup.m,E:\\STRSDEmo\\signature.m,E:\\STRSDEmo\\verify.m}"
 */

package STRS;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;
import java.util.*;
import java.io.Serializable;

/**
 * The <code>STRSSignature</code> class provides a Java interface to MATLAB functions. 
 * The interface is compiled from the following files:
 * <pre>
 *  E:\\STRSDEmo\\hash.m
 *  E:\\STRSDEmo\\keygen.m
 *  E:\\STRSDEmo\\setup.m
 *  E:\\STRSDEmo\\signature.m
 *  E:\\STRSDEmo\\verify.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a <code>STRSSignature</code> 
 * instance when it is no longer needed to ensure that native resources allocated by this 
 * class are properly freed.
 * @version 0.0
 */
public class STRSSignature extends MWComponentInstance<STRSSignature> implements Serializable
{
    /**
     * Tracks all instances of this class to ensure their dispose method is
     * called on shutdown.
     */
    private static final Set<Disposable> sInstances = new HashSet<Disposable>();

    /**
     * Maintains information used in calling the <code>hash</code> MATLAB function.
     */
    private static final MWFunctionSignature sHashSignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "hash",
                                /* max inputs = */ 2,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>keygen</code> MATLAB function.
     */
    private static final MWFunctionSignature sKeygenSignature =
        new MWFunctionSignature(/* max outputs = */ 3,
                                /* has varargout = */ false,
                                /* function name = */ "keygen",
                                /* max inputs = */ 4,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>setup</code> MATLAB function.
     */
    private static final MWFunctionSignature sSetupSignature =
        new MWFunctionSignature(/* max outputs = */ 6,
                                /* has varargout = */ false,
                                /* function name = */ "setup",
                                /* max inputs = */ 0,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>signature</code> MATLAB function.
     */
    private static final MWFunctionSignature sSignatureSignature =
        new MWFunctionSignature(/* max outputs = */ 7,
                                /* has varargout = */ false,
                                /* function name = */ "signature",
                                /* max inputs = */ 9,
                                /* has varargin = */ false);
    /**
     * Maintains information used in calling the <code>verify</code> MATLAB function.
     */
    private static final MWFunctionSignature sVerifySignature =
        new MWFunctionSignature(/* max outputs = */ 1,
                                /* has varargout = */ false,
                                /* function name = */ "verify",
                                /* max inputs = */ 12,
                                /* has varargin = */ false);

    /**
     * Shared initialization implementation - private
     * @throws MWException An error has occurred during the function call.
     */
    private STRSSignature (final MWMCR mcr) throws MWException
    {
        super(mcr);
        // add this to sInstances
        synchronized(STRSSignature.class) {
            sInstances.add(this);
        }
    }

    /**
     * Constructs a new instance of the <code>STRSSignature</code> class.
     * @throws MWException An error has occurred during the function call.
     */
    public STRSSignature() throws MWException
    {
        this(STRSMCRFactory.newInstance());
    }
    
    private static MWComponentOptions getPathToComponentOptions(String path)
    {
        MWComponentOptions options = new MWComponentOptions(new MWCtfExtractLocation(path),
                                                            new MWCtfDirectorySource(path));
        return options;
    }
    
    /**
     * @deprecated Please use the constructor {@link #STRSSignature(MWComponentOptions componentOptions)}.
     * The <code>com.mathworks.toolbox.javabuilder.MWComponentOptions</code> class provides an API to set the
     * path to the component.
     * @param pathToComponent Path to component directory.
     * @throws MWException An error has occurred during the function call.
     */
    @Deprecated
    public STRSSignature(String pathToComponent) throws MWException
    {
        this(STRSMCRFactory.newInstance(getPathToComponentOptions(pathToComponent)));
    }
    
    /**
     * Constructs a new instance of the <code>STRSSignature</code> class. Use this 
     * constructor to specify the options required to instantiate this component.  The 
     * options will be specific to the instance of this component being created.
     * @param componentOptions Options specific to the component.
     * @throws MWException An error has occurred during the function call.
     */
    public STRSSignature(MWComponentOptions componentOptions) throws MWException
    {
        this(STRSMCRFactory.newInstance(componentOptions));
    }
    
    /** Frees native resources associated with this object */
    public void dispose()
    {
        try {
            super.dispose();
        } finally {
            synchronized(STRSSignature.class) {
                sInstances.remove(this);
            }
        }
    }
    
    /**
     * Calls dispose method for each outstanding instance of this class.
     */
    public static void disposeAllInstances()
    {
        synchronized(STRSSignature.class) {
            for (Disposable i : sInstances) i.dispose();
            sInstances.clear();
        }
    }

    /**
     * Provides the interface for calling the <code>hash</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % HASH - Convert an input variable into a message digest using any of
     * %        several common hash algorithms
     * %
     * % USAGE: h = hash(inp,'meth')
     * %
     * % inp  = input variable, of any of the following classes:
     * %        char, uint8, logical, double, single, int8, uint8,
     * %        int16, uint16, int32, uint32, int64, uint64
     * % h    = hash digest output, in hexadecimal notation
     * % meth = hash algorithm, which is one of the following:
     * %        MD2, MD5, SHA-1, SHA-256, SHA-384, or SHA-512  
     * %
     * % NOTES: (1) If the input is a string or uint8 variable, it is hashed
     * %            as usual for a byte stream. Other classes are converted into
     * %            their byte-stream values. In other words, the hash of the
     * %            following will be identical:
     * %                     'abc'
     * %                     uint8('abc')
     * %                     char([97 98 99])
     * %            The hash of the follwing will be different from the above,
     * %            because class "double" uses eight byte elements:
     * %                     double('abc')
     * %                     [97 98 99]
     * %            You can avoid this issue by making sure that your inputs
     * %            are strings or uint8 arrays.
     * %        (2) The name of the hash algorithm may be specified in lowercase
     * %            and/or without the hyphen, if desired. For example,
     * %            h=hash('my text to hash','sha256');
     * %        (3) Carefully tested, but no warranty. Use at your own risk.
     * %        (4) Michael Kleder, Nov 2005
     * %
     * % EXAMPLE:
     * %
     * % algs={'MD2','MD5','SHA-1','SHA-256','SHA-384','SHA-512'};
     * % for n=1:6
     * %     h=hash('my sample text',algs{n});
     * %     disp([algs{n} ' (' num2str(length(h)*4) ' bits):'])
     * %     disp(h)
     * % end
	 * }
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void hash(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sHashSignature);
    }

    /**
     * Provides the interface for calling the <code>hash</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % HASH - Convert an input variable into a message digest using any of
     * %        several common hash algorithms
     * %
     * % USAGE: h = hash(inp,'meth')
     * %
     * % inp  = input variable, of any of the following classes:
     * %        char, uint8, logical, double, single, int8, uint8,
     * %        int16, uint16, int32, uint32, int64, uint64
     * % h    = hash digest output, in hexadecimal notation
     * % meth = hash algorithm, which is one of the following:
     * %        MD2, MD5, SHA-1, SHA-256, SHA-384, or SHA-512  
     * %
     * % NOTES: (1) If the input is a string or uint8 variable, it is hashed
     * %            as usual for a byte stream. Other classes are converted into
     * %            their byte-stream values. In other words, the hash of the
     * %            following will be identical:
     * %                     'abc'
     * %                     uint8('abc')
     * %                     char([97 98 99])
     * %            The hash of the follwing will be different from the above,
     * %            because class "double" uses eight byte elements:
     * %                     double('abc')
     * %                     [97 98 99]
     * %            You can avoid this issue by making sure that your inputs
     * %            are strings or uint8 arrays.
     * %        (2) The name of the hash algorithm may be specified in lowercase
     * %            and/or without the hyphen, if desired. For example,
     * %            h=hash('my text to hash','sha256');
     * %        (3) Carefully tested, but no warranty. Use at your own risk.
     * %        (4) Michael Kleder, Nov 2005
     * %
     * % EXAMPLE:
     * %
     * % algs={'MD2','MD5','SHA-1','SHA-256','SHA-384','SHA-512'};
     * % for n=1:6
     * %     h=hash('my sample text',algs{n});
     * %     disp([algs{n} ' (' num2str(length(h)*4) ' bits):'])
     * %     disp(h)
     * % end
	 * }
	 * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void hash(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sHashSignature);
    }

    /**
     * Provides the standard interface for calling the <code>hash</code> MATLAB function with 
     * 2 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % HASH - Convert an input variable into a message digest using any of
     * %        several common hash algorithms
     * %
     * % USAGE: h = hash(inp,'meth')
     * %
     * % inp  = input variable, of any of the following classes:
     * %        char, uint8, logical, double, single, int8, uint8,
     * %        int16, uint16, int32, uint32, int64, uint64
     * % h    = hash digest output, in hexadecimal notation
     * % meth = hash algorithm, which is one of the following:
     * %        MD2, MD5, SHA-1, SHA-256, SHA-384, or SHA-512  
     * %
     * % NOTES: (1) If the input is a string or uint8 variable, it is hashed
     * %            as usual for a byte stream. Other classes are converted into
     * %            their byte-stream values. In other words, the hash of the
     * %            following will be identical:
     * %                     'abc'
     * %                     uint8('abc')
     * %                     char([97 98 99])
     * %            The hash of the follwing will be different from the above,
     * %            because class "double" uses eight byte elements:
     * %                     double('abc')
     * %                     [97 98 99]
     * %            You can avoid this issue by making sure that your inputs
     * %            are strings or uint8 arrays.
     * %        (2) The name of the hash algorithm may be specified in lowercase
     * %            and/or without the hyphen, if desired. For example,
     * %            h=hash('my text to hash','sha256');
     * %        (3) Carefully tested, but no warranty. Use at your own risk.
     * %        (4) Michael Kleder, Nov 2005
     * %
     * % EXAMPLE:
     * %
     * % algs={'MD2','MD5','SHA-1','SHA-256','SHA-384','SHA-512'};
     * % for n=1:6
     * %     h=hash('my sample text',algs{n});
     * %     disp([algs{n} ' (' num2str(length(h)*4) ' bits):'])
     * %     disp(h)
     * % end
	 * }
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] hash(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sHashSignature), 
                    sHashSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>keygen</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % 采样私钥
	 * }
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void keygen(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sKeygenSignature);
    }

    /**
     * Provides the interface for calling the <code>keygen</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % 采样私钥
	 * }
	 * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void keygen(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sKeygenSignature);
    }

    /**
     * Provides the standard interface for calling the <code>keygen</code> MATLAB function with 
     * 4 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % 采样私钥
	 * }
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] keygen(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sKeygenSignature), 
                    sKeygenSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>setup</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % 设置公共参数
	 * }
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void setup(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sSetupSignature);
    }

    /**
     * Provides the interface for calling the <code>setup</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % 设置公共参数
	 * }
	 * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void setup(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sSetupSignature);
    }

    /**
     * Provides the standard interface for calling the <code>setup</code> MATLAB function with 
     * 0 input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % 设置公共参数
	 * }
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] setup(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sSetupSignature), 
                    sSetupSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>signature</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * %N = size(Lpk,1);
	 * }
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void signature(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sSignatureSignature);
    }

    /**
     * Provides the interface for calling the <code>signature</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * %N = size(Lpk,1);
	 * }
	 * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void signature(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sSignatureSignature);
    }

    /**
     * Provides the standard interface for calling the <code>signature</code> MATLAB function with 
     * 9 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * %N = size(Lpk,1);
	 * }
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] signature(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sSignatureSignature), 
                    sSignatureSignature);
        return lhs;
    }
    /**
     * Provides the interface for calling the <code>verify</code> MATLAB function 
     * where the first argument, an instance of List, receives the output of the MATLAB function and
     * the second argument, also an instance of List, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % Compute di = d0 + i·θ, for all i∈[N].
     *     % Note that all di are stored in an N × (n × d) dimensional matrix.
	 * }
     * </pre>
     * @param lhs List in which to return outputs. Number of outputs (nargout) is
     * determined by allocated size of this List. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs List containing inputs. Number of inputs (nargin) is determined
     * by the allocated size of this List. Input arguments may be passed as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or
     * as arrays of any supported Java type. Arguments passed as Java types are
     * converted to MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void verify(List lhs, List rhs) throws MWException
    {
        fMCR.invoke(lhs, rhs, sVerifySignature);
    }

    /**
     * Provides the interface for calling the <code>verify</code> MATLAB function 
     * where the first argument, an Object array, receives the output of the MATLAB function and
     * the second argument, also an Object array, provides the input to the MATLAB function.
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % Compute di = d0 + i·θ, for all i∈[N].
     *     % Note that all di are stored in an N × (n × d) dimensional matrix.
	 * }
	 * </pre>
     * @param lhs array in which to return outputs. Number of outputs (nargout)
     * is determined by allocated size of this array. Outputs are returned as
     * sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>.
     * Each output array should be freed by calling its <code>dispose()</code>
     * method.
     *
     * @param rhs array containing inputs. Number of inputs (nargin) is
     * determined by the allocated size of this array. Input arguments may be
     * passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     * @throws MWException An error has occurred during the function call.
     */
    public void verify(Object[] lhs, Object[] rhs) throws MWException
    {
        fMCR.invoke(Arrays.asList(lhs), Arrays.asList(rhs), sVerifySignature);
    }

    /**
     * Provides the standard interface for calling the <code>verify</code> MATLAB function with 
     * 12 comma-separated input arguments.
     * Input arguments may be passed as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of
     * any supported Java type. Arguments passed as Java types are converted to
     * MATLAB arrays according to default conversion rules.
     *
     * <p>
     * Description as provided by the author of the MATLAB function:
     * </p>
     * <pre>
     * {@literal
	 * % Compute di = d0 + i·θ, for all i∈[N].
     *     % Note that all di are stored in an N × (n × d) dimensional matrix.
	 * }
     * </pre>
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     * @return Array of length nargout containing the function outputs. Outputs
     * are returned as sub-classes of
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>. Each output array
     * should be freed by calling its <code>dispose()</code> method.
     * @throws MWException An error has occurred during the function call.
     */
    public Object[] verify(int nargout, Object... rhs) throws MWException
    {
        Object[] lhs = new Object[nargout];
        fMCR.invoke(Arrays.asList(lhs), 
                    MWMCR.getRhsCompat(rhs, sVerifySignature), 
                    sVerifySignature);
        return lhs;
    }
}
