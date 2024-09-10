/*
 * MATLAB Compiler: 8.4 (R2022a)
 * Date: Mon Sep  9 21:45:40 2024
 * Arguments: 
 * "-B""macro_default""-W""java:STRS,STRSSignature""-T""link:lib""-d""D:\\demo\\STRS\\for_testing""class{STRSSignature:E:\\STRSDEmo\\hash.m,E:\\STRSDEmo\\keygen.m,E:\\STRSDEmo\\setup.m,E:\\STRSDEmo\\signature.m,E:\\STRSDEmo\\verify.m}"
 */

package STRS;

import com.mathworks.toolbox.javabuilder.pooling.Poolable;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The <code>STRSSignatureRemote</code> class provides a Java RMI-compliant interface to 
 * MATLAB functions. The interface is compiled from the following files:
 * <pre>
 *  E:\\STRSDEmo\\hash.m
 *  E:\\STRSDEmo\\keygen.m
 *  E:\\STRSDEmo\\setup.m
 *  E:\\STRSDEmo\\signature.m
 *  E:\\STRSDEmo\\verify.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a 
 * <code>STRSSignatureRemote</code> instance when it is no longer needed to ensure that 
 * native resources allocated by this class are properly freed, and the server-side proxy 
 * is unexported.  (Failure to call dispose may result in server-side threads not being 
 * properly shut down, which often appears as a hang.)  
 *
 * This interface is designed to be used together with 
 * <code>com.mathworks.toolbox.javabuilder.remoting.RemoteProxy</code> to automatically 
 * generate RMI server proxy objects for instances of STRS.STRSSignature.
 */
public interface STRSSignatureRemote extends Poolable
{
    /**
     * Provides the standard interface for calling the <code>hash</code> MATLAB function 
     * with 2 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
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
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] hash(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>keygen</code> MATLAB 
     * function with 4 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * {@literal 
	 * % 采样私钥
	 * }
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] keygen(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>setup</code> MATLAB function 
     * with 0 input argument.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * {@literal 
	 * % 设置公共参数
	 * }
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] setup(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>signature</code> MATLAB 
     * function with 9 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * {@literal 
	 * %N = size(Lpk,1);
	 * }
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] signature(int nargout, Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>verify</code> MATLAB 
     * function with 12 input arguments.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * {@literal 
	 * % Compute di = d0 + i·θ, for all i∈[N].
     *     % Note that all di are stored in an N × (n × d) dimensional matrix.
	 * }
     * </pre>
     *
     * @param nargout Number of outputs to return.
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] verify(int nargout, Object... rhs) throws RemoteException;
  
    /** 
     * Frees native resources associated with the remote server object 
     * @throws java.rmi.RemoteException An error has occurred during the function call or in communication with the server.
     */
    void dispose() throws RemoteException;
}
