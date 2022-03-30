package org.ldk.structs;

import org.ldk.impl.bindings;
import org.ldk.enums.*;
import org.ldk.util.*;
import java.util.Arrays;
import java.lang.ref.Reference;
import javax.annotation.Nullable;


/**
 * When on-chain outputs are created by rust-lightning (which our counterparty is not able to
 * claim at any point in the future) an event is generated which you must track and be able to
 * spend on-chain. The information needed to do this is provided in this enum, including the
 * outpoint describing which txid and output index is available, the full output which exists at
 * that txid/index, and any keys or other information required to sign.
 */
@SuppressWarnings("unchecked") // We correctly assign various generic arrays
public class SpendableOutputDescriptor extends CommonBase {
	private SpendableOutputDescriptor(Object _dummy, long ptr) { super(ptr); }
	@Override @SuppressWarnings("deprecation")
	protected void finalize() throws Throwable {
		super.finalize();
		if (ptr != 0) { bindings.SpendableOutputDescriptor_free(ptr); }
	}
	static SpendableOutputDescriptor constr_from_ptr(long ptr) {
		bindings.LDKSpendableOutputDescriptor raw_val = bindings.LDKSpendableOutputDescriptor_ref_from_ptr(ptr);
		if (raw_val.getClass() == bindings.LDKSpendableOutputDescriptor.StaticOutput.class) {
			return new StaticOutput(ptr, (bindings.LDKSpendableOutputDescriptor.StaticOutput)raw_val);
		}
		if (raw_val.getClass() == bindings.LDKSpendableOutputDescriptor.DelayedPaymentOutput.class) {
			return new DelayedPaymentOutput(ptr, (bindings.LDKSpendableOutputDescriptor.DelayedPaymentOutput)raw_val);
		}
		if (raw_val.getClass() == bindings.LDKSpendableOutputDescriptor.StaticPaymentOutput.class) {
			return new StaticPaymentOutput(ptr, (bindings.LDKSpendableOutputDescriptor.StaticPaymentOutput)raw_val);
		}
		assert false; return null; // Unreachable without extending the (internal) bindings interface
	}

	/**
	 * An output to a script which was provided via KeysInterface directly, either from
	 * `get_destination_script()` or `get_shutdown_scriptpubkey()`, thus you should already know
	 * how to spend it. No secret keys are provided as rust-lightning was never given any key.
	 * These may include outputs from a transaction punishing our counterparty or claiming an HTLC
	 * on-chain using the payment preimage or after it has timed out.
	 */
	public final static class StaticOutput extends SpendableOutputDescriptor {
		/**
		 * The outpoint which is spendable
		*/
		public final OutPoint outpoint;
		/**
		 * The output which is referenced by the given outpoint.
		*/
		public final TxOut output;
		private StaticOutput(long ptr, bindings.LDKSpendableOutputDescriptor.StaticOutput obj) {
			super(null, ptr);
			long outpoint = obj.outpoint;
			OutPoint outpoint_hu_conv = null; if (outpoint < 0 || outpoint > 4096) { outpoint_hu_conv = new OutPoint(null, outpoint); }
			outpoint_hu_conv.ptrs_to.add(this);
			this.outpoint = outpoint_hu_conv;
			long output = obj.output;
			TxOut output_conv = new TxOut(null, output);
			this.output = output_conv;
		}
	}
	/**
	 * An output to a P2WSH script which can be spent with a single signature after a CSV delay.
	 * 
	 * The witness in the spending input should be:
	 * <BIP 143 signature> <empty vector> (MINIMALIF standard rule) <provided witnessScript>
	 * 
	 * Note that the nSequence field in the spending input must be set to to_self_delay
	 * (which means the transaction is not broadcastable until at least to_self_delay
	 * blocks after the outpoint confirms).
	 * 
	 * These are generally the result of a \"revocable\" output to us, spendable only by us unless
	 * it is an output from an old state which we broadcast (which should never happen).
	 * 
	 * To derive the delayed_payment key which is used to sign for this input, you must pass the
	 * holder delayed_payment_base_key (ie the private key which corresponds to the pubkey in
	 * Sign::pubkeys().delayed_payment_basepoint) and the provided per_commitment_point to
	 * chan_utils::derive_private_key. The public key can be generated without the secret key
	 * using chan_utils::derive_public_key and only the delayed_payment_basepoint which appears in
	 * Sign::pubkeys().
	 * 
	 * To derive the revocation_pubkey provided here (which is used in the witness
	 * script generation), you must pass the counterparty revocation_basepoint (which appears in the
	 * call to Sign::ready_channel) and the provided per_commitment point
	 * to chan_utils::derive_public_revocation_key.
	 * 
	 * The witness script which is hashed and included in the output script_pubkey may be
	 * regenerated by passing the revocation_pubkey (derived as above), our delayed_payment pubkey
	 * (derived as above), and the to_self_delay contained here to
	 * chan_utils::get_revokeable_redeemscript.
	 */
	public final static class DelayedPaymentOutput extends SpendableOutputDescriptor {
		public final DelayedPaymentOutputDescriptor delayed_payment_output;
		private DelayedPaymentOutput(long ptr, bindings.LDKSpendableOutputDescriptor.DelayedPaymentOutput obj) {
			super(null, ptr);
			long delayed_payment_output = obj.delayed_payment_output;
			DelayedPaymentOutputDescriptor delayed_payment_output_hu_conv = null; if (delayed_payment_output < 0 || delayed_payment_output > 4096) { delayed_payment_output_hu_conv = new DelayedPaymentOutputDescriptor(null, delayed_payment_output); }
			delayed_payment_output_hu_conv.ptrs_to.add(this);
			this.delayed_payment_output = delayed_payment_output_hu_conv;
		}
	}
	/**
	 * An output to a P2WPKH, spendable exclusively by our payment key (ie the private key which
	 * corresponds to the public key in Sign::pubkeys().payment_point).
	 * The witness in the spending input, is, thus, simply:
	 * <BIP 143 signature> <payment key>
	 * 
	 * These are generally the result of our counterparty having broadcast the current state,
	 * allowing us to claim the non-HTLC-encumbered outputs immediately.
	 */
	public final static class StaticPaymentOutput extends SpendableOutputDescriptor {
		public final StaticPaymentOutputDescriptor static_payment_output;
		private StaticPaymentOutput(long ptr, bindings.LDKSpendableOutputDescriptor.StaticPaymentOutput obj) {
			super(null, ptr);
			long static_payment_output = obj.static_payment_output;
			StaticPaymentOutputDescriptor static_payment_output_hu_conv = null; if (static_payment_output < 0 || static_payment_output > 4096) { static_payment_output_hu_conv = new StaticPaymentOutputDescriptor(null, static_payment_output); }
			static_payment_output_hu_conv.ptrs_to.add(this);
			this.static_payment_output = static_payment_output_hu_conv;
		}
	}
	long clone_ptr() {
		long ret = bindings.SpendableOutputDescriptor_clone_ptr(this.ptr);
		Reference.reachabilityFence(this);
		return ret;
	}

	/**
	 * Creates a copy of the SpendableOutputDescriptor
	 */
	public SpendableOutputDescriptor clone() {
		long ret = bindings.SpendableOutputDescriptor_clone(this.ptr);
		Reference.reachabilityFence(this);
		if (ret >= 0 && ret <= 4096) { return null; }
		SpendableOutputDescriptor ret_hu_conv = SpendableOutputDescriptor.constr_from_ptr(ret);
		ret_hu_conv.ptrs_to.add(this);
		return ret_hu_conv;
	}

	/**
	 * Utility method to constructs a new StaticOutput-variant SpendableOutputDescriptor
	 */
	public static SpendableOutputDescriptor static_output(OutPoint outpoint, TxOut output) {
		long ret = bindings.SpendableOutputDescriptor_static_output(outpoint == null ? 0 : outpoint.ptr & ~1, output.ptr);
		Reference.reachabilityFence(outpoint);
		Reference.reachabilityFence(output);
		if (ret >= 0 && ret <= 4096) { return null; }
		SpendableOutputDescriptor ret_hu_conv = SpendableOutputDescriptor.constr_from_ptr(ret);
		ret_hu_conv.ptrs_to.add(ret_hu_conv);
		return ret_hu_conv;
	}

	/**
	 * Utility method to constructs a new DelayedPaymentOutput-variant SpendableOutputDescriptor
	 */
	public static SpendableOutputDescriptor delayed_payment_output(DelayedPaymentOutputDescriptor a) {
		long ret = bindings.SpendableOutputDescriptor_delayed_payment_output(a == null ? 0 : a.ptr & ~1);
		Reference.reachabilityFence(a);
		if (ret >= 0 && ret <= 4096) { return null; }
		SpendableOutputDescriptor ret_hu_conv = SpendableOutputDescriptor.constr_from_ptr(ret);
		ret_hu_conv.ptrs_to.add(ret_hu_conv);
		return ret_hu_conv;
	}

	/**
	 * Utility method to constructs a new StaticPaymentOutput-variant SpendableOutputDescriptor
	 */
	public static SpendableOutputDescriptor static_payment_output(StaticPaymentOutputDescriptor a) {
		long ret = bindings.SpendableOutputDescriptor_static_payment_output(a == null ? 0 : a.ptr & ~1);
		Reference.reachabilityFence(a);
		if (ret >= 0 && ret <= 4096) { return null; }
		SpendableOutputDescriptor ret_hu_conv = SpendableOutputDescriptor.constr_from_ptr(ret);
		ret_hu_conv.ptrs_to.add(ret_hu_conv);
		return ret_hu_conv;
	}

	/**
	 * Serialize the SpendableOutputDescriptor object into a byte array which can be read by SpendableOutputDescriptor_read
	 */
	public byte[] write() {
		byte[] ret = bindings.SpendableOutputDescriptor_write(this.ptr);
		Reference.reachabilityFence(this);
		return ret;
	}

	/**
	 * Read a SpendableOutputDescriptor from a byte array, created by SpendableOutputDescriptor_write
	 */
	public static Result_SpendableOutputDescriptorDecodeErrorZ read(byte[] ser) {
		long ret = bindings.SpendableOutputDescriptor_read(ser);
		Reference.reachabilityFence(ser);
		if (ret >= 0 && ret <= 4096) { return null; }
		Result_SpendableOutputDescriptorDecodeErrorZ ret_hu_conv = Result_SpendableOutputDescriptorDecodeErrorZ.constr_from_ptr(ret);
		return ret_hu_conv;
	}

}
