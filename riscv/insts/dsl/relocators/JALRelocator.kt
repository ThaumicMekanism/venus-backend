package venusbackend.riscv.insts.dsl.relocators

import venusbackend.assembler.DebugInfo
import venusbackend.riscv.InstructionField
import venusbackend.riscv.MachineCode

private object JALRelocator32 : Relocator32 {
    override operator fun invoke(mcode: MachineCode, pc: Int, target: Int, dbg: DebugInfo) {
        val imm = target - pc
        mcode[InstructionField.IMM_20] = imm shr 20
        mcode[InstructionField.IMM_10_1] = imm shr 1
        mcode[InstructionField.IMM_19_12] = imm shr 12
        mcode[InstructionField.IMM_11_J] = imm shr 11
    }
}

val JALRelocator = Relocator(JALRelocator32, NoRelocator64)
