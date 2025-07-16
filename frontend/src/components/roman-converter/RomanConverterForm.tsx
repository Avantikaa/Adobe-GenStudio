import React from "react";
import { Flex, TextField, Button } from "@adobe/react-spectrum";

interface Props {
    inputValue: string;
    setInputValue: (value: string) => void;
    onConvert: () => void;
}

const RomanConverterForm: React.FC<Props> = ({ inputValue, setInputValue, onConvert }) => {
    return (
        <Flex direction="column" alignItems="center" justifyContent="center" height="100vh">
            <Flex direction="column" gap="size-200" alignItems="center" maxWidth="size-3600">
                <TextField
                    label="Enter a number (1-3999)"
                    value={inputValue}
                    onChange={setInputValue}
                    type="number"
                    width="size-3000"
                />
                <Button variant="cta" onPress={onConvert}>
                    Convert to Roman Numeral
                </Button>
            </Flex>
        </Flex>
    );
};

export default RomanConverterForm;
